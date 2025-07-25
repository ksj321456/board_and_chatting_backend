package kr.ac.hansung.cse.board_and_chatting;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LogoutTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 로그아웃_성공() throws Exception {
        // 1. 로그인 요청으로 세션 생성
        String loginRequestJson = """
            {
              "userId": "root",
              "password": "1234"
            }
        """;

        MvcResult loginResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestJson))
                .andExpect(status().isOk())
                .andReturn();

        // 2. 로그인 시 생성된 세션을 얻어옴
        MockHttpSession session = (MockHttpSession) loginResult.getRequest().getSession(false);

        // 3. 로그아웃 요청 수행 (세션 포함)
        mockMvc.perform(post("/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("LOG_OUT_SUCCESS")) // 성공 코드 넣기
                .andExpect(jsonPath("$.message").value("로그아웃에 성공했습니다.")); // 메시지는 SuccessStatus 기준
    }
}
