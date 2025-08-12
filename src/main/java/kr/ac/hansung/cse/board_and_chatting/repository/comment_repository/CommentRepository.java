package kr.ac.hansung.cse.board_and_chatting.repository.comment_repository;

import kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.CommentCountWithOneArticleDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save (Comment comment);

    List<CommentCountWithOneArticleDto> findCommentCountCustom(List<Long> board_comment_id);
}
