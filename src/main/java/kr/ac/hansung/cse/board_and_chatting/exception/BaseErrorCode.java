package kr.ac.hansung.cse.board_and_chatting.exception;

public interface BaseErrorCode {

    ErrorReasonDto getReason();

    ErrorReasonDto getReasonHttpStatus();
}
