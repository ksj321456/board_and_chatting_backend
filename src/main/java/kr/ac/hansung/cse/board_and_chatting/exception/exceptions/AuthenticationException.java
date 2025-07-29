package kr.ac.hansung.cse.board_and_chatting.exception.exceptions;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;

@Getter
public class AuthenticationException extends DataIntegrityViolationException {

    private ErrorStatus errorStatus;

    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
}
