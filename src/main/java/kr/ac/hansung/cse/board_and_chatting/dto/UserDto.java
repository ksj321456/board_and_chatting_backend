package kr.ac.hansung.cse.board_and_chatting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

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
