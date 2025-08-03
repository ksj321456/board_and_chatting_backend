package kr.ac.hansung.cse.board_and_chatting.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@Builder
// HTTP body에 담을 객체
// NULL인 JSON field는 생략, NULL이 아닌 field와 값들만 응답 JSON으로 전송
@JsonInclude(JsonInclude.Include.NON_NULL)
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
