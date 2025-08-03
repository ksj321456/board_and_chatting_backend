package kr.ac.hansung.cse.board_and_chatting.configuration;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import kr.ac.hansung.cse.board_and_chatting.repository.board_repository.BoardJpaRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.user_repository.UserJpaRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//@Component
public class ProjectInitializer {

    private BoardJpaRepository boardJpaRepository;
    private UserJpaRepository userJpaRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ProjectInitializer(BoardJpaRepository boardJpaRepository, UserJpaRepository userJpaRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.boardJpaRepository = boardJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        User user = User.builder()
                .nickname("관리자")
                .userId("root")
                .password(bCryptPasswordEncoder.encode("1234"))
                .authority(Authority.ADMIN)
                .createdAt(LocalDateTime.now())
                .build();
        userJpaRepository.save(user);


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
