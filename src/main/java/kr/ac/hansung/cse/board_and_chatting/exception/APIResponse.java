package kr.ac.hansung.cse.board_and_chatting.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class APIResponse<T> {

    private final HttpStatus status;

    private String code;

    private String message;

    // HTTP body에 담을 객체
    private T result;


    // 클라이언트에 넘겨줄 ResponseEntity 객체 생성 메소드
    public static <T> ResponseEntity<APIResponse<T>> toResponseEntity(APIResponse<T> apiResponse) {
        return ResponseEntity.status(apiResponse.getStatus())
                .body(APIResponse.<T>builder()
                        .status(apiResponse.getStatus())
                        .code(apiResponse.getCode())
                        .message(apiResponse.getMessage())
                        .result(apiResponse.getResult())
                        .build()
                );
    }
}
