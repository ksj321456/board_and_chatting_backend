package kr.ac.hansung.cse.board_and_chatting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
