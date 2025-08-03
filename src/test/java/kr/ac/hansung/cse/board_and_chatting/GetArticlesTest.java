package kr.ac.hansung.cse.board_and_chatting;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.repository.BoardRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.UserRepository;
import kr.ac.hansung.cse.board_and_chatting.service.BoardService;
import kr.ac.hansung.cse.board_and_chatting.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional(readOnly = true)
@AutoConfigureMockMvc
public class GetArticlesTest {

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public GetArticlesTest(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("1. 게시글 불러오기 성공")
    public void test_get_articles_success() {
        Optional<User> userOptional = userRepository.findByUserId("root");
        User user = userOptional.orElse(null);

        List<Board> boards = boardRepository.findAllWithUser(PageRequest.of(0, 10, Sort.by("createdAt").descending()));
        Assertions.assertNotNull(boards);
    }

    @Test
    @DisplayName("2. 필수 쿼리 파라미터 없음(page)")
    public void test_get_articles_fail_page() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-articles")
                .param("size", String.valueOf(20))
        ).andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("3. 필수 쿼리 파라미터 없음(size)")
    public void test_get_articles_fail_size() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get-articles")
                .param("page", String.valueOf(1))
        ).andExpect(status().isBadRequest());
    }
}
