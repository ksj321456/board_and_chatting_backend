package kr.ac.hansung.cse.board_and_chatting.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ReasonDto {

    private final HttpStatus status;

    private boolean isSuccess;

    private String code;

    private String message;

    public boolean isSuccess() {
        return isSuccess;
    }
}
