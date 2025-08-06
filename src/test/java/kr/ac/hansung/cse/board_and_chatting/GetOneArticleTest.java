package kr.ac.hansung.cse.board_and_chatting;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ServerInternalException;
import kr.ac.hansung.cse.board_and_chatting.repository.board_repository.BoardRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.user_repository.UserRepository;
import kr.ac.hansung.cse.board_and_chatting.service.board_service.BoardService;
import kr.ac.hansung.cse.board_and_chatting.service.user_service.UserService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class GetOneArticleTest {

    private final Logger logger = LoggerFactory.getLogger(GetOneArticleTest.class);

    private UserRepository userRepository;
    private BoardRepository boardRepository;

    private long startTime;
    private long endTime;

    @Autowired
    public GetOneArticleTest(UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @BeforeEach
    public void setUp() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    public void tearDown() {
        endTime = System.currentTimeMillis();
        logger.info("Time taken: " + (endTime - startTime) + "ms");
    }


    @Test
    @DisplayName("1. 게시글 불러오기 성공")
    public void test_get_one_article_success() {
        Assertions.assertNotNull(boardRepository.findBoardByIdCustom(60000L));
    }

    @Test
    @DisplayName("2. 게시글 불러오기 실패")
    public void test_get_one_article_fail() {
        Assertions.assertThrows(ServerInternalException.class, () -> boardRepository.findBoardByIdCustom(60001L));
    }

    private User getFakeUser() {
        return User.builder()
                .id(4000L)
                .userId("testuser")
                .password("pw")
                .nickname("tester")
                .authority(Authority.USER)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Board getFakeBoard(User user) {
        return Board.builder()
                .id(99999L)
                .title("테스트 제목")
                .category(Category.FREE)
                .content("테스트 내용")
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
