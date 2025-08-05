package kr.ac.hansung.cse.board_and_chatting.service.board_service;

import kr.ac.hansung.cse.board_and_chatting.dto.request_dto.BoardRequestDto;
import kr.ac.hansung.cse.board_and_chatting.dto.response_dto.BoardResponseDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardService {
    // 실제 기능 메서드만 정의합니다.
    Board saveArticle(BoardRequestDto.CreateArticleRequest createArticleRequest, User user);

    BoardResponseDto.GeneralArticlesResponseDto getArticle(int page, int size);

    BoardResponseDto.GeneralArticlesResponseDto getArticlesWithTitle(String title, int page, int size);
}
