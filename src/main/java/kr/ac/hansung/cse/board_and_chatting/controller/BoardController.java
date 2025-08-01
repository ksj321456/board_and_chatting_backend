package kr.ac.hansung.cse.board_and_chatting.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.ac.hansung.cse.board_and_chatting.dto.BoardDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.exception.APIResponse;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.AuthenticationException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.ValidationException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.exception.status.SuccessStatus;
import kr.ac.hansung.cse.board_and_chatting.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 작성 로직
    @PostMapping("/create-article")
    public ResponseEntity<?> createArticle(@Valid @RequestBody BoardDto.CreateArticleRequest createArticleRequest,
                                           BindingResult bindingResult,
                                                     HttpServletRequest request
                                                     ) {
        // Validation 검증
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult, ErrorStatus.NOT_SUFFICIENT_DATA_FOR_CREATING_ARITLCE);
        }


        HttpSession session = request.getSession();
        // session이 없으면 죽어.
        if (session.getAttribute("user") == null) {
            throw new AuthenticationException(ErrorStatus.NO_AUTHENTICATION);
        }

        User user = (User) session.getAttribute("user");

        Board board = boardService.saveArticle(createArticleRequest, user);
        return APIResponse.toResponseEntity(
                APIResponse.builder()
                        .status(SuccessStatus.CREATE_ARTICLE_SUCCESS.getStatus())
                        .code(SuccessStatus.CREATE_ARTICLE_SUCCESS.getCode())
                        .message(SuccessStatus.CREATE_ARTICLE_SUCCESS.getMessage())
                        .build()
        );
    }
}
