package kr.ac.hansung.cse.board_and_chatting.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // 일반적인 서버 오류
    INTERNAL_BAD_REQUEST(HttpStatus.BAD_REQUEST, "GENERAL_4001", "서버에서 확인할 문제"),

    // 사용자 인증, 인가 예외
    NOT_SUFFICIENT_DATA_FOR_SIGN_UP(HttpStatus.BAD_REQUEST, "SIGNUP_4001", "회원가입을 위한 필수 정보가 빠져 있습니다."),
    ALREADY_EXISTS_USER(HttpStatus.BAD_REQUEST, "SIGNUP_4002", "이미 존재하는 회원입니다.")


    ;

    private final HttpStatus status;

    private final String code;

    private final String message;
}
