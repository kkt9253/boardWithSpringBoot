package takBoard.board.data.dao;

import takBoard.board.data.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {
    List<Board> findAll();

    Optional<Board> findById(Long number);

    Board selectBoard(Long number);

    Board insertBoard(Board board);

    Board updateBoard(Long number, String title, String context) throws Exception;

    void deleteBoard(Long number) throws Exception;



}
