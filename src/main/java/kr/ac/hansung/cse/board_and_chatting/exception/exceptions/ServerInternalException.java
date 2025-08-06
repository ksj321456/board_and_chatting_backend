package kr.ac.hansung.cse.board_and_chatting.exception.exceptions;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ServerInternalException extends GeneralException{
    public ServerInternalException(ErrorStatus status) {
        super(status);
    }
}
