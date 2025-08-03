package kr.ac.hansung.cse.board_and_chatting.repository.board_repository;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepository {
    Board save(Board board);

    Board findBoardById(Long boardId);

    Page<Board> findAllWithUser(Pageable pageable);
}
