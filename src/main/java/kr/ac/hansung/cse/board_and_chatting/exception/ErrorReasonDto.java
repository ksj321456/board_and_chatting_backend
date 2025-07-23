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
public class ErrorReasonDto {

    private final HttpStatus status;

    private String code;

    private String message;

    public static ResponseEntity<ErrorReasonDto> toResponseEntity(ErrorStatus status) {
        return ResponseEntity
                .status(status.getStatus())
                .body(ErrorReasonDto.builder()
                        .status(status.getStatus())
                        .code(status.getCode())
                        .message(status.getMessage())
                        .build()
                );
    }
}
