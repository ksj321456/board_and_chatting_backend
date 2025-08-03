package kr.ac.hansung.cse.board_and_chatting.service.board_service;

import kr.ac.hansung.cse.board_and_chatting.dto.request_dto.BoardRequestDto;
import kr.ac.hansung.cse.board_and_chatting.dto.response_dto.BoardResponseDto;
import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.entity.User;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.AuthenticationException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import kr.ac.hansung.cse.board_and_chatting.repository.board_repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board saveArticle(BoardRequestDto.CreateArticleRequest createArticleRequest, User user) {
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

    public BoardResponseDto.GeneralArticlesResponseDto getArticle(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Board> boards = boardRepository.findAllWithUser(pageable);
        long totalPages = boards.getTotalElements();
        List<BoardResponseDto.ArticleResponseDto> articles = new ArrayList<>();

        boards.forEach(board -> {
            BoardResponseDto.ArticleResponseDto articleResponseDto = BoardResponseDto.ArticleResponseDto.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .category(board.getCategory())
                    .author(board.getUser().getNickname())
                    .createdAt(board.getCreatedAt())
                    .updatedAt(board.getUpdatedAt())
                    .build();
            articles.add(articleResponseDto);
        });

        BoardResponseDto.GeneralArticlesResponseDto generalArticlesResponseDto = BoardResponseDto.GeneralArticlesResponseDto
                .builder()
                .totalPages(totalPages)
                .articles(articles)
                .build();

        return generalArticlesResponseDto;
    }
}
