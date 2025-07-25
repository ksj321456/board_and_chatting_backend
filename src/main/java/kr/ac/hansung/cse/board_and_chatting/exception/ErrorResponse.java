package kr.ac.hansung.cse.board_and_chatting.exception;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private final HttpStatus status;

    private String code;

    private String message;

    private Object result;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorStatus status) {
        return ResponseEntity
                .status(status.getStatus())
                .body(ErrorResponse.builder()
                        .status(status.getStatus())
                        .code(status.getCode())
                        .message(status.getMessage())
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorStatus status, Object result) {
        return ResponseEntity
                .status(status.getStatus())
                .body(ErrorResponse.builder()
                        .status(status.getStatus())
                        .code(status.getCode())
                        .message(status.getMessage())
                        .result(result)
                        .build());
    }
}
