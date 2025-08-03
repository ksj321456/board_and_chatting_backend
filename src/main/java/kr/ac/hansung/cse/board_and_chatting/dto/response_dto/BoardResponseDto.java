package kr.ac.hansung.cse.board_and_chatting.dto.response_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ArticleResponseDto {
        private String title;

        private String content;

        @JsonProperty("category")
        private Category category;

        // 작성자 이름
        private String author;

        // 전체 페이지 갯수
        private Long totalPages;

        // 첫 페이지인가?
        private boolean isFirst;

        // 마지막 페이지인지?
        private boolean isLast;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
