package kr.ac.hansung.cse.board_and_chatting.repository;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import kr.ac.hansung.cse.board_and_chatting.exception.exceptions.AuthenticationException;
import kr.ac.hansung.cse.board_and_chatting.exception.status.ErrorStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public class BoardJpaRepository implements BoardRepository {

    private final JpaBoardRepository boardRepository;

    public BoardJpaRepository(JpaBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Override
    public Board save(Board board) {
        try{
            return boardRepository.save(board);
        } catch (DataIntegrityViolationException e) {
            throw new AuthenticationException(ErrorStatus.ALREADY_EXISTS_ARTICLE);
        }
    }

    @Override
    public Board findBoardById(Long boardId) {
        Optional<Board> boardOptional = boardRepository.findBoardById(boardId);
        if (boardOptional.isPresent()) {
            return boardOptional.get();
        }
        return null;
    }
}
