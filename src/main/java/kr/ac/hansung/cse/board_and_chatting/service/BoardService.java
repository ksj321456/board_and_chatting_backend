package kr.ac.hansung.cse.board_and_chatting.service;

import kr.ac.hansung.cse.board_and_chatting.dto.BoardDto;
import kr.ac.hansung.cse.board_and_chatting.dto.response_dto.BoardResponseDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BoardService {
    // 실제 기능 메서드만 정의합니다.
    Board saveArticle(BoardDto.CreateArticleRequest createArticleRequest, User user);

    List<BoardResponseDto.ArticleResponseDto> getArticle(int page, int size);
}
