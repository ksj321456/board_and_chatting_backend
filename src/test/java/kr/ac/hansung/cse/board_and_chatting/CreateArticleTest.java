package kr.ac.hansung.cse.board_and_chatting;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.hansung.cse.board_and_chatting.dto.BoardDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.AuthenticationException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.CreateArticleException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ValidationException;
import kr.ac.hansung.cse.board_and_chatting.repository.UserRepository;
import kr.ac.hansung.cse.board_and_chatting.service.BoardService;
import kr.ac.hansung.cse.board_and_chatting.service.BoardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CreateArticleTest {

    private BoardService boardService;
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public CreateArticleTest(BoardService boardService, UserRepository userRepository) {
        this.boardService = boardService;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("1. 게시글 작성 성공")
    void createArticle_01() {
        User user = User.builder()
                .userId("test1")
                .password("test_pw")
                .nickname("tester")
                .authority(Authority.USER)
                .createdAt(LocalDateTime.now())
                .build();

        user = userRepository.save(user);

        BoardDto.CreateArticleRequest createArticleRequest = BoardDto.CreateArticleRequest.builder()
                .title("제목1")
                .content("내용1")
                .category(Category.FREE)
                .build();

        boardService.saveArticle(createArticleRequest, user);
    }

    @Test
    @DisplayName("2. 게시글 작성 실패(중복된 제목)")
    void createArticle_02() {
        User user = userRepository.findByUserId("root").get();
        System.out.println(user);

        BoardDto.CreateArticleRequest createArticleRequest = BoardDto.CreateArticleRequest.builder()
                .title("제목1")
                .content("내용1")
                .category(Category.FREE)
                .build();

        Assertions.assertThrows(AuthenticationException.class, () -> boardService.saveArticle(createArticleRequest, user));
    }

    @Test
    @DisplayName("Validation 실패 - 필수 필드 누락")
    void createArticle_validationFail() throws Exception {
        // given: 잘못된 요청 본문 (title 누락)
        String jsonRequest = """
                {
                    "content": "내용1",
                    "category": "FREE"
                }
                """;

        mockMvc.perform(post("/api/create-article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .sessionAttr("user", getFakeUser()))  // 세션에 유저도 넣어줌
                .andExpect(status().isBadRequest());
    }

    private User getFakeUser() {
        return User.builder()
                .id(1L)
                .userId("testuser")
                .password("pw")
                .nickname("tester")
                .authority(Authority.USER)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
