package kr.ac.hansung.cse.board_and_chatting;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ValidationException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.repository.board_repository.BoardRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.user_repository.UserRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional(readOnly = true)
@AutoConfigureMockMvc
public class GetArticlesTest {

    private static final Logger log = LoggerFactory.getLogger(GetArticlesTest.class);
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private long startTime;
    private long endTime;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public GetArticlesTest(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void setUp() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    public void tearDown() {
        endTime = System.currentTimeMillis();
        log.info("Time taken: " + (endTime - startTime) + "ms");
    }


//    @Test
//    @DisplayName("1. 게시글 불러오기 성공")
//    public void test_get_articles_success() {
//        Optional<User> userOptional = userRepository.findByUserId("root");
//        User user = userOptional.orElse(null);
//
//        Page<Board> boards = boardRepository.findAllWithUser(PageRequest.of(0, 10, Sort.by("createdAt").descending()));
//        Assertions.assertNotNull(boards);
//    }

    @Test
    @DisplayName("2. 필수 쿼리 파라미터 없음(page)")
    public void test_get_articles_fail_page() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-articles")
                .param("size", String.valueOf(20))
                .sessionAttr("user", getFakeUser())
        ).andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("3. 필수 쿼리 파라미터 없음(size)")
    public void test_get_articles_fail_size() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-articles")
                .param("page", String.valueOf(1)).sessionAttr("user", getFakeUser())).andExpect(status().isBadRequest());
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
