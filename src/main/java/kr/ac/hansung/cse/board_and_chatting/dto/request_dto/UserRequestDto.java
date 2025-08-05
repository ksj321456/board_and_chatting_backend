package kr.ac.hansung.cse.board_and_chatting.dto.request_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    private String userId;

    private byte[] userPicture;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoginDto {

        @NotBlank(message = "아이디는 필수 입력 항목입니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
        private String password;
    }

}
