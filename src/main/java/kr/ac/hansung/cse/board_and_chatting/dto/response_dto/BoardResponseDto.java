package kr.ac.hansung.cse.board_and_chatting.dto.response_dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class GeneralArticlesResponseDto {
        private Long totalPages;

        private List<ArticleResponseDto> articles;
    }

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

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class OneArticleResponseDto {
        private String title;

        private String content;

        private Category category;

        private String author;

        // 수정할 수 있는가? => 즉, 권한이 있는가?
        private boolean canUpdate;

        // 삭제할 수 있는가? => 즉, 권한이 있는가?
        private boolean canDelete;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
