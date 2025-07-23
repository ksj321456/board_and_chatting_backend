package kr.ac.hansung.cse.board_and_chatting.service;

import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.exception.SignUpForException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 회원가입 진행 서비스 메소드
    @Transactional
    public User signUpService(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUserIdAndPassword(userDto.getUserId(), userDto.getPassword());

        if (userOptional.isPresent()) {
            throw new SignUpForException(ErrorStatus.ALREADY_EXISTS_USER);
        }

        if (userDto.getUserId().equals("ADMIN")) {
            User user = User.builder()
                    .nickname(userDto.getNickname())
                    .userId(userDto.getUserId())
                    .password(userDto.getPassword())
                    .authority(Authority.ADMIN)
                    .userPicture(userDto.getUser_picture())
                    .build();
            userRepository.save(user);
            return user;
        } else {
            User user = User.builder()
                    .nickname(userDto.getNickname())
                    .userId(userDto.getUserId())
                    .password(userDto.getPassword())
                    .authority(Authority.USER)
                    .userPicture(userDto.getUser_picture())
                    .build();
            userRepository.save(user);
            return user;
        }
    }
}
