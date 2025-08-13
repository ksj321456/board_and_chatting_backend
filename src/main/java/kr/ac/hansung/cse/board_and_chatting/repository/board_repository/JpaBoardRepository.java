package kr.ac.hansung.cse.board_and_chatting.repository.board_repository;

import kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.board_dto.BoardDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long boardId);

    @Query(value = "SELECT b.id AS boardId, b.title AS title, b.category AS category, u.nickname AS author, b.like AS like, " +
            "b.dislike AS dislike, b.createdAt AS createdAt, b.updatedAt AS updatedAt " +
            "FROM Board b " +
            "INNER JOIN b.user u " +
            "ORDER BY b.createdAt DESC",
    countQuery = "SELECT COUNT(b) FROM Board b"
    )
    Page<BoardDto> findAllWithUser(Pageable pageable);

    @Query(value = "SELECT b.id AS boardId, b.title AS title, b.category AS category, u.nickname AS author, b.like AS like, " +
            "b.dislike AS dislike, b.createdAt AS createdAt, b.updatedAt AS updatedAt " +
            "FROM Board b " +
            "INNER JOIN b.user u " +
            "WHERE b.title LIKE CONCAT('%', :title, '%') " +
            "ORDER BY b.createdAt DESC",
            countQuery = "SELECT COUNT(b) FROM Board b WHERE b.title LIKE CONCAT('%', :title, '%')")
    Page<BoardDto> findAllByTitleCustom(@Param("title") String title, Pageable pageable);

    @Query(value = "SELECT b.id AS boardId, b.title AS title, b.category AS category, u.nickname AS author, b.like AS like, " +
            "b.dislike AS dislike, b.createdAt AS createdAt, b.updatedAt AS updatedAt " +
            "FROM Board b " +
            "INNER JOIN b.user u " +
            "WHERE b.title LIKE CONCAT('%', :title, '%') OR b.content LIKE CONCAT('%', :content, '%')" +
            "ORDER BY b.createdAt DESC",
    countQuery = "SELECT COUNT(b) FROM Board b WHERE b.title LIKE CONCAT('%', :title, '%') OR b.content LIKE CONCAT('%', :content, '%') "
    )
    Page<BoardDto> findAllByTitleAndContentCustom(@Param("title") String title, @Param("content") String content, Pageable pageable);

    // Fetch join으로 Board와 매핑되어 있는 User 객체도 함께 GET
    @Query(value = "SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :boardId")
    Optional<Board> findBoardByIdCustom(@Param("boardId") Long boardId);
}
