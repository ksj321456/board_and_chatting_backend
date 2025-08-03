package kr.ac.hansung.cse.board_and_chatting.configuration;

import jakarta.annotation.PostConstruct;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import kr.ac.hansung.cse.board_and_chatting.repository.BoardJpaRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class BoardInitializer {

    private BoardJpaRepository boardJpaRepository;
    private UserJpaRepository userJpaRepository;

    public BoardInitializer(BoardJpaRepository boardJpaRepository, UserJpaRepository userJpaRepository) {
        this.boardJpaRepository = boardJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    // Board 더미 데이터 생성 메소드
    public void init() {
        Optional<User> userOptional = userJpaRepository.findByUserId("root");
        User user = userOptional.orElseThrow(() -> new NullPointerException("BoardInitializer Bean 생성 중 User NPE 예외 발생"));
        for (int i = 0; i < 3000; i++) {
            Board board = Board.builder()
                    .title("제목 " + i)
                    .category(Category.FREE)
                    .content("내용 " + i)
                    .user(user)
                    .build();

            boardJpaRepository.save(board);
        }
    }
}
