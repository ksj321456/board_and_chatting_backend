package kr.ac.hansung.cse.board_and_chatting.repository;

import kr.ac.hansung.cse.board_and_chatting.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaBoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long boardId);

    Page<Board> findAll(Pageable pageable);
}
