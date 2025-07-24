package kr.ac.hansung.cse.board_and_chatting.exception;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogInException extends RuntimeException {
    ErrorStatus status;
}
