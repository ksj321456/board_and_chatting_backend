package kr.ac.hansung.cse.board_and_chatting.repository;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;

public interface BoardRepository {
    Board save(Board board);

    Board findBoardById(Long boardId);
}
