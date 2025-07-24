package kr.ac.hansung.cse.board_and_chatting.service;

import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.exception.LogInException;
import kr.ac.hansung.cse.board_and_chatting.exception.SignUpForException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 진행 서비스 메소드
    @Transactional
    public User signUpService(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUserId(userDto.getUserId());

        // 이미 존재하는 회원일 경우 예외처리
        if (userOptional.isPresent()) {
            throw new SignUpForException(ErrorStatus.ALREADY_EXISTS_USER);
        }

        if (userDto.getUserId().equals("ADMIN")) {
            User user = User.builder()
                    .nickname(userDto.getNickname())
                    .userId(userDto.getUserId())
                    .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .authority(Authority.ADMIN)
                    .userPicture(userDto.getUserPicture())
                    .build();
            userRepository.save(user);
            return user;
        } else {
            User user = User.builder()
                    .nickname(userDto.getNickname())
                    .userId(userDto.getUserId())
                    .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .authority(Authority.USER)
                    .userPicture(userDto.getUserPicture())
                    .build();
            userRepository.save(user);
            return user;
        }
    }

    @Transactional
    public User loginService(UserDto.LoginDto userDto) {
        Optional<User> userOptional = userRepository.findByUserId(userDto.getUserId());

        // DB에 해당 User 정보가 없다면 예외처리
        userOptional.orElseThrow(() -> new LogInException(ErrorStatus.NOT_EXISTING_USER));

        userOptional.ifPresent(user -> {
            if (!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword()) && !user.getUserId().equals(userDto.getUserId())) {
                throw new LogInException(ErrorStatus.WRONG_BOTH_INFO);
            }
            if (!user.getUserId().equals(userDto.getUserId())) {
                throw new LogInException(ErrorStatus.WRONG_USER_ID);
            }
            if (!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                throw new LogInException(ErrorStatus.WRONG_PASSWORD);
            }
        });

        // user 객체 반환
        return userOptional.get();
    }
}
