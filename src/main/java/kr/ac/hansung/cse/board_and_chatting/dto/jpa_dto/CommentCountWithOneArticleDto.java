package kr.ac.hansung.cse.board_and_chatting.dto.jpa_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommentCountWithOneArticleDto {

    private Long boardId;

    private Long commentCount;
}
