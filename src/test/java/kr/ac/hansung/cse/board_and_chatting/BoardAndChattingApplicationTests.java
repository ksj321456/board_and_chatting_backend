package kr.ac.hansung.cse.board_and_chatting;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.SignUpForException;
import kr.ac.hansung.cse.board_and_chatting.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class BoardAndChattingApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("1. User 객체 저장(userId가 ADMIN인지 아닌지도 판별)")
    void createUser() {
        User user = User.builder()
                .userId("ksj321456")
                .password("1234")
                .nickname("엠피아")
                .build();

        User savedUser = userService.signUpService(User.toDto(user));
        if (savedUser != null) {
            Assertions.assertEquals(Authority.USER, savedUser.getAuthority());
        }
    }


    @Test
    @DisplayName("2. User 객체 저장_2(ADMIN이어야 함.)")
    void createUser2() {
        User user = User.builder()
                .userId("ADMIN")
                .password("1234")
                .nickname("엠피아")
                .build();

        User savedUser = userService.signUpService(User.toDto(user));
        if (savedUser != null) {
            Assertions.assertEquals(Authority.ADMIN, savedUser.getAuthority());
        }
    }

    @Test
    @DisplayName("3. User 객체 저장시 중복 검사")
    void createUser3() {
        User user = User.builder()
                .userId("test_user01")
                .password("encrypted_password")
                .nickname("엠피아")
                .build();

        Assertions.assertThrows(SignUpForException.class, () -> userService.signUpService(User.toDto(user)));
    }

    @Test
    @DisplayName("4. User 객체 저장시 비밀번호 암호화 검사")
    void createUser4() {
        User user = User.builder()
                .userId("hgfdhdf")
                .password("fdsfdsfsd")
                .nickname("fdsfsdf")
                .build();
        User savedUser = userService.signUpService(User.toDto(user));
        Assertions.assertNotEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    @DisplayName("5. 회원가입 성공 후 Session 저장 테스트")
    void testSignUpStoresUserInSession() throws Exception {
        // Given: 유효한 UserDto
        UserDto userDto = UserDto.builder()
                .userId("test_user02")
                .password("my_password")
                .nickname("테스터")
                .build();

        String json = objectMapper.writeValueAsString(userDto);

        // When: 회원가입 요청 실행 + 응답 결과 가져오기
        MvcResult result = mockMvc.perform(post("/sign_up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        // Then: 세션에서 "user" 속성이 존재하는지 확인
        MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
        Object user = session.getAttribute("user");

        assertNotNull(user, "세션에 user 객체가 저장되어야 합니다.");
        assertTrue(user instanceof User, "세션에 저장된 객체는 User 타입이어야 합니다.");
    }

}
