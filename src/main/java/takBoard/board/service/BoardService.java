package takBoard.board.service;

import takBoard.board.data.dto.BoardDTO;
import takBoard.board.data.dto.BoardResponseDTO;
import takBoard.board.data.entity.Board;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BoardService {

    List<Board> findAll();

    Optional<Board> findById(Long number);

    BoardResponseDTO getBoard(Long number);

    BoardResponseDTO saveBoard(BoardDTO boardDTO) throws IOException;

    String savePhoto(BoardDTO boardDTO) throws IOException;

    BoardResponseDTO changeBoard(Long number, String name, String context) throws Exception;

    void deleteBoard(Long number) throws Exception;


}
