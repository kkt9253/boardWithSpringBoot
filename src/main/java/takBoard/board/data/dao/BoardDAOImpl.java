package takBoard.board.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import takBoard.board.data.entity.Board;
import takBoard.board.data.repository.BoardRepository;

import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDAO{

    private final BoardRepository boardRepository;

    @Autowired
    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 해당 부분 추가, 오류나면 확인
    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> findById(Long number) {
        return boardRepository.findById(number);
    }

    @Override
    public Board insertBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board selectBoard(Long number) {
        return boardRepository.findById(number).orElse(null);
    }

    @Override
    public Board updateBoard(Long number, String title, String context) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(number);

        if (selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            // selectedBoard.get() -> selectedBoard에 값이 있을 경우 객체 반환하고, 없을 경우 NoSuchElementException 반환

            board.setTitle(title);
            board.setContext(context);
            board.setUpdatedTime(LocalDateTime.now());
            return boardRepository.save(board);
        } else {
            throw new Exception("Board not found");
        }
    }

    @Override
    public void deleteBoard(Long number) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(number);

        if (selectedBoard.isPresent()) {
            boardRepository.deleteById(number);
        } else {
            throw new Exception("Board not found");
        }
    }
}
