package kr.ac.hansung.cse.board_and_chatting.exception.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // 일반적인 서버 오류
    INTERNAL_BAD_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR, "GENERAL_500", "서버에서 확인할 문제"),
    NO_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "GENERAL_401", "먼저 로그인 및 회원가입을 해야합니다."),

    // 사용자 인증, 인가 예외
    NOT_SUFFICIENT_DATA_FOR_SIGN_UP(HttpStatus.BAD_REQUEST, "SIGNUP_4001", "회원가입을 위한 필수 정보가 빠져 있습니다."),
    ALREADY_EXISTS_USER(HttpStatus.BAD_REQUEST, "SIGNUP_4002", "이미 존재하는 회원입니다."),

    // 로그인 예외
    NOT_SUFFICIENT_DATA_FOR_LOG_IN(HttpStatus.BAD_REQUEST, "LOGIN_4001", "로그인을 위한 필수 정보가 빠져 있습니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "LOGIN_4O02", "비밀번호가 틀렸습니다."),
    WRONG_USER_ID(HttpStatus.BAD_REQUEST, "LOGIN_4O03", "아이디가 틀렸습니다."),
    WRONG_BOTH_INFO(HttpStatus.BAD_REQUEST, "LOGIN_4004", "아이디와 비밀번호 모두 틀렸습니다."),
    NOT_EXISTING_USER(HttpStatus.BAD_REQUEST, "LOGIN_4005", "해당 계정은 존재하지 않습니다."),

    // 게시판 예외
    NOT_SUFFICIENT_DATA_FOR_CREATING_ARITLCE(HttpStatus.BAD_REQUEST, "BOARD_4001", "게시판 작성을 위한 필수 정보가 빠져 있습니다."),
    ALREADY_EXISTS_ARTICLE(HttpStatus.BAD_REQUEST, "BOARD_4002", "같은 제목의 게시글이 이미 있습니다.")
    ;

    private final HttpStatus status;

    private final String code;

    private final String message;
}
