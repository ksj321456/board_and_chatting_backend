package kr.ac.hansung.cse.board_and_chatting.exception.exceptions;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;

public class CreateArticleException extends GeneralException{
    public CreateArticleException(ErrorStatus status) {
        super(status);
    }
}
