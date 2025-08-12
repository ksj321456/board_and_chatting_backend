package kr.ac.hansung.cse.board_and_chatting.repository.comment_repository;

import kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.CommentCountWithOneArticleDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.CommentCountWithOneArticleDto(b.id, COUNT(c.id)) " +
            "FROM Board b LEFT JOIN b.comments c " +
            "WHERE b.id IN :boardIds " +
            "GROUP BY b.id " +
            "ORDER BY b.createdAt DESC")
    List<CommentCountWithOneArticleDto> findCommentCountCustom(@Param("boardIds") List<Long> boardIds);
}
