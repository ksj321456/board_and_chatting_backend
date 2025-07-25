package kr.ac.hansung.cse.board_and_chatting.exception.exceptions;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SignUpForException extends GeneralException {

    public SignUpForException(ErrorStatus status) {
        super(status);
    }
}
