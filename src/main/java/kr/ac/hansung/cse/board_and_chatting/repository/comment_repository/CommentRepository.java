package kr.ac.hansung.cse.board_and_chatting.repository.comment_repository;

import kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.comment_dto.CommentCountWithOneArticleDto;
import kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.comment_dto.CommentDto;
import kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.comment_dto.CommentsInOneArticle;
import kr.ac.hansung.cse.board_and_chatting.entity.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepository {

    Comment save (Comment comment);

    List<CommentDto> findCommentCountCustom(List<Long> board_comment_id);

    List<CommentsInOneArticle> findCommentByBoardIdCustom(Long boardId, Pageable pageable);
}
