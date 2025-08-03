package kr.ac.hansung.cse.board_and_chatting.repository;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long boardId);

    @Query(value = "SELECT b FROM Board b JOIN FETCH b.user ORDER BY b.createdAt DESC",
    countQuery = "SELECT COUNT(b) FROM Board b"
    )
    Page<Board> findAllWithUser(Pageable pageable);
}
