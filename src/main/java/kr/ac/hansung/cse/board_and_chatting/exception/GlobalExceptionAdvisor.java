package kr.ac.hansung.cse.board_and_chatting.exception;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvisor {

    @ExceptionHandler(SignUpForException.class)
    public ResponseEntity<ErrorReasonDto> handleSignUpForException(SignUpForException e) {
        return ErrorReasonDto.toResponseEntity(e.getStatus());
    }
}
