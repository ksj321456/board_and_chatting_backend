package kr.ac.hansung.cse.board_and_chatting;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.LogInException;
import kr.ac.hansung.cse.board_and_chatting.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class LogInTests {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("1. 로그인 실패 로직(비밀번호 틀림)")
    void logInFail_01() {
        UserDto.LoginDto loginDto = UserDto.LoginDto.builder()
                .userId("hong123")
                .password("gfsfgsfd")
                .build();

        Assertions.assertThrows(LogInException.class, () -> userServiceImpl.loginService(loginDto));
    }

    @Test
    @DisplayName("2. 로그인 실패 로직(아이디, 비밀번호 모두 틀림)")
    void logInFail_02() {
        UserDto.LoginDto loginDto = UserDto.LoginDto.builder()
                .userId("fdsfsdfdsfdsfsdf")
                .password("Gfsgfdgsdfasdfsd")
                .build();
        Assertions.assertThrows(LogInException.class, () -> userServiceImpl.loginService(loginDto));
    }

    @Test
    @DisplayName("3. 로그인 실패 로직(아이디 틀림")
    void logInFail_03() {
        UserDto.LoginDto loginDto = UserDto.LoginDto.builder()
                .userId("fdsfsdfdsfdsfsdf")
                .password("1234")
                .build();
        Assertions.assertThrows(LogInException.class, () -> userServiceImpl.loginService(loginDto));
    }

    @Test
    @DisplayName("4. 로그인 성공")
    void logInSuccess() {
        UserDto.LoginDto loginDto = UserDto.LoginDto.builder()
                .userId("root")
                .password("1234")
                .build();

        Assertions.assertInstanceOf(User.class, userServiceImpl.loginService(loginDto));
    }
}
