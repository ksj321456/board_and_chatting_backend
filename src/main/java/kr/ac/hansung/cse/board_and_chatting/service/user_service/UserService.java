package kr.ac.hansung.cse.board_and_chatting.service.user_service;

import kr.ac.hansung.cse.board_and_chatting.dto.request_dto.UserRequestDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {
    public User signUpService(UserRequestDto userDto);

    public User loginService(UserRequestDto.LoginDto userDto);
}
