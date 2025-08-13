package kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto.comment_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"commentCount"})
public class CommentsInOneArticle implements CommentDto {

    private Long commentId;

    private Long boardId;

    private String content;

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
