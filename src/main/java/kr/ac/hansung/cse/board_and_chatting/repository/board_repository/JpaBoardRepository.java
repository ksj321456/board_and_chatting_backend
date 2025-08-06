package kr.ac.hansung.cse.board_and_chatting.repository.board_repository;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long boardId);

    @Query(value = "SELECT b FROM Board b JOIN FETCH b.user ORDER BY b.createdAt DESC",
    countQuery = "SELECT COUNT(b) FROM Board b"
    )
    Page<Board> findAllWithUser(Pageable pageable);

    @Query(value = "SELECT b FROM Board b JOIN FETCH b.user WHERE b.title LIKE CONCAT('%', :title, '%') ORDER BY b.createdAt DESC",
            countQuery = "SELECT COUNT(b) FROM Board b WHERE b.title LIKE CONCAT('%', :title, '%')")
    Page<Board> findAllByTitleCustom(@Param("title") String title, Pageable pageable);

    // Fetch join으로 Board와 매핑되어 있는 User 객체도 함께 GET
    @Query(value = "SELECT b FROM Board b JOIN FETCH b.user WHERE b.id = :id")
    Optional<Board> findBoardByIdCustom(@Param("id") Long id);
}
