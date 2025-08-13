package kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.comment_dto;

public interface CommentDto {

    default Long getCommentCount() {
        return null;
    }
}
