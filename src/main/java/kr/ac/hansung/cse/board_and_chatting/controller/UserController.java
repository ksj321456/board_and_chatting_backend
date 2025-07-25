package kr.ac.hansung.cse.board_and_chatting.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.exception.APIResponse;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.LogInException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.SignUpForException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ValidationException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.exception.status.SuccessStatus;
import kr.ac.hansung.cse.board_and_chatting.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        log.info(userDto.toString());

        // 유효성 검사 실패 후 예외 처리
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult, ErrorStatus.NOT_SUFFICIENT_DATA_FOR_SIGN_UP);
        }

        User user = userService.signUpService(userDto);
        // user 객체가 NULL 값을 가질 일은 없지만 만약 갖게 된다면 예외 처리
        if (user == null) {
            throw new SignUpForException(ErrorStatus.INTERNAL_BAD_REQUEST);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return APIResponse.toResponseEntity(
                APIResponse.builder()
                        .status(SuccessStatus.SIGN_UP_SUCCESS.getStatus())
                        .code(SuccessStatus.SIGN_UP_SUCCESS.getCode())
                        .message(SuccessStatus.SIGN_UP_SUCCESS.getMessage())
                        .result(userDto)
                        .build()
        );

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto.LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request) {
        log.info(loginDto.toString());
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult, ErrorStatus.NOT_SUFFICIENT_DATA_FOR_LOG_IN);
        }

        // 로그인 처리
        User user = userService.loginService(loginDto);

        // 세션 새로 생성
        HttpSession session = request.getSession(true);  // 없으면 새로 만듦
        session.setAttribute("user", user);

        return APIResponse.toResponseEntity(
                APIResponse.builder()
                        .status(SuccessStatus.LOG_IN_SUCCESS.getStatus())
                        .code(SuccessStatus.LOG_IN_SUCCESS.getCode())
                        .message(SuccessStatus.LOG_IN_SUCCESS.getMessage())
                        .result(loginDto.getUserId())
                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 있으면 정상적으로 만료
        }

        return APIResponse.toResponseEntity(
                APIResponse.builder()
                        .status(SuccessStatus.LOG_OUT_SUCCESS.getStatus())
                        .code(SuccessStatus.LOG_OUT_SUCCESS.getCode())
                        .message(SuccessStatus.LOG_OUT_SUCCESS.getMessage())
                        .build()
        );
    }
}
