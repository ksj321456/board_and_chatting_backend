package kr.ac.hansung.cse.board_and_chatting.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionAdvisor {

    @ExceptionHandler(SignUpForException.class)
    public ResponseEntity<ErrorResponse> handleSignUpForException(SignUpForException e) {
        log.error(String.valueOf(e.getStatus().getStatus()));
        log.error(e.getStatus().getCode());
        log.error(e.getStatus().getMessage());
        return ErrorResponse.toResponseEntity(e.getStatus());
    }

    @ExceptionHandler(LogInException.class)
    public ResponseEntity<ErrorResponse> handleLogInException(LogInException e) {
        log.error(String.valueOf(e.getStatus().getStatus()));
        log.error(e.getStatus().getCode());
        log.error(e.getStatus().getMessage());
        return ErrorResponse.toResponseEntity(e.getStatus());
    }
}
