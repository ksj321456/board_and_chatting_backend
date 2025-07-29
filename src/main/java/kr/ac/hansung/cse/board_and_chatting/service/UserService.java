package kr.ac.hansung.cse.board_and_chatting.service;

import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.repository.JpaUserRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {
    public User signUpService(UserDto userDto);

    public User loginService(UserDto.LoginDto userDto);
}
