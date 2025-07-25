package kr.ac.hansung.cse.board_and_chatting.exception.exceptions;

import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

    private BindingResult bindingResult;

    private ErrorStatus errorStatus;
}
