package kr.ac.hansung.cse.board_and_chatting.exception;

import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.GeneralException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionAdvisor<T> {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> handleSignUpForException(GeneralException e) {
        log.error(String.valueOf(e.getStatus().getStatus()));
        log.error(e.getStatus().getCode());
        log.error(e.getStatus().getMessage());
        return ErrorResponse.toResponseEntity(e.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {

        List<Map<String, String>> errorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> Map.of(
                        "field", fieldError.getField(),
                        "reason", fieldError.getDefaultMessage()
                ))
                .toList();

        return ErrorResponse.toResponseEntity(e.getErrorStatus(), Map.of("errors", errorList));

    }
}
