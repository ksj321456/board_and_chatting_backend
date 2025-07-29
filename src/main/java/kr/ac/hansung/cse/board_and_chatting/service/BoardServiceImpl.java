package kr.ac.hansung.cse.board_and_chatting.service;

import kr.ac.hansung.cse.board_and_chatting.dto.BoardDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.AuthenticationException;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.CreateArticleException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.repository.BoardRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board saveArticle(BoardDto.CreateArticleRequest createArticleRequest, User user) {
        Board board = Board.builder()
                .title(createArticleRequest.getTitle())
                .category(createArticleRequest.getCategory())
                .content(createArticleRequest.getContent())
                .user(user)
                .build();

        // 중복된 Title의 게시글이 이미 있다면 => 예외 처리
        if (boardRepository.findBoardById(board.getId()) != null) {
            throw new AuthenticationException(ErrorStatus.ALREADY_EXISTS_ARTICLE);
        }

        return boardRepository.save(board);
    }
}
