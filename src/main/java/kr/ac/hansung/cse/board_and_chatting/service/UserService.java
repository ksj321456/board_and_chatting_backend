package kr.ac.hansung.cse.board_and_chatting.service;

import kr.ac.hansung.cse.board_and_chatting.repository.JpaUserRepository;
import kr.ac.hansung.cse.board_and_chatting.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@AllArgsConstructor
@Getter
public abstract class UserService {

    protected final UserRepository userRepository;

    // AppConfig에서 Bean으로 등록
    protected final BCryptPasswordEncoder bCryptPasswordEncoder;

}
