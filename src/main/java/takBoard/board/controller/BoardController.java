package takBoard.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import takBoard.board.data.dto.BoardDTO;
import takBoard.board.data.dto.BoardResponseDTO;
import takBoard.board.data.entity.Board;
import takBoard.board.service.BoardService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public String home() {
        return "home";
    }

    @GetMapping("/read")
    public ResponseEntity<BoardResponseDTO> getBoard(Long number) {
        BoardResponseDTO boardResponseDTO = boardService.getBoard(number);

        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
    }

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @GetMapping("/list")
    public String readList(Model model) {
        List<Board> members = boardService.findAll();
        model.addAttribute("members", members);
        return "list";
    }

    @GetMapping("/listDetail")
    public String readListDetail(Model model) {
        List<Board> listMembers = boardService.findAll();
        model.addAttribute("listMembers", listMembers);
        return "listDetail";
    }

    @GetMapping("/detail")
    public String readDetail(@RequestParam("number") Long number, Model model) {
        Optional<Board> detailBoard = boardService.findById(number);
        model.addAttribute("detailBoard", detailBoard);
        return "detail";
    }

    @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.saveBoard(boardDTO);
        return "redirect:/board"; // 게시물 목록 페이지로 리다이렉트
    }


    @PostMapping("/update")
    public String changeBoard(@RequestParam("number") Long number, @RequestParam("title") String title, @RequestParam("context") String context) throws Exception {
        boardService.changeBoard(number, title, context);

        return "home";
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam Long number) throws Exception {
        boardService.deleteBoard(number);

        return "home";
    }
}
