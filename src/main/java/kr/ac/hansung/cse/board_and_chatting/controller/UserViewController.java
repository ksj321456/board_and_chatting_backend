package kr.ac.hansung.cse.board_and_chatting.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.exception.APIResponse;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.SignUpForException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ValidationException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.exception.status.SuccessStatus;
import kr.ac.hansung.cse.board_and_chatting.service.UserServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc")
public class UserViewController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup"; // templates/signup.html
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute("userDto") @Valid UserDto userDto,
                               BindingResult bindingResult,
                               @RequestParam(value = "pictureFile", required = false) MultipartFile pictureFile,
                               HttpSession session,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            if (pictureFile != null && !pictureFile.isEmpty()) {
                userDto.setUserPicture(pictureFile.getBytes());
            }
        } catch (IOException e) {
            model.addAttribute("error", "이미지 처리 중 오류가 발생했습니다.");
            return "signup";
        }

        User user = userServiceImpl.signUpService(userDto);
        session.setAttribute("user", user);
        return "redirect:/board"; // 가입 후 홈으로 리다이렉트
    }

    @PostMapping("/login") // 로그인 폼에서 보내는 요청 처리
    public String loginSubmit(@RequestParam String userId,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        var loginDto = new UserDto.LoginDto(userId, password);
        User user = userServiceImpl.loginService(loginDto);

        if (user == null) {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "home";
        }

        session.setAttribute("user", user);
        return "redirect:board";
    }
}


