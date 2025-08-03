package kr.ac.hansung.cse.board_and_chatting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class BoardDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CreateArticleRequest {

        @NotBlank(message = "제목은 필수 입력입니다.")
        private String title;

        @NotBlank(message = "내용은 필수 입력입니다.")
        private String content;

        @NotNull(message = "카테코리는 필수 입력입니다.")
        @JsonProperty("category")
        private Category category;
    }

    // 게시글 불러올 때 쿼리 파라미터 DTO
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetArticleRequestParameters {
        @NotNull(message = "page 파라미터는 필수입니다.")
        @Min(value = 0, message = "page 파라미터의 값은 0 이상이어야 합니다.")
        private Integer page;

        @NotNull(message = "size 파라미터는 필수입니다.")
        @Min(value = 1, message = "size 파라미터의 값은 1 이상이어야 합니다.")
        private Integer size;
    }
}
