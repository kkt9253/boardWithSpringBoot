package takBoard.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import takBoard.board.data.dto.BoardDTO;
import takBoard.board.data.dto.BoardResponseDTO;
import takBoard.board.data.dto.ChangeBoardDto;
import takBoard.board.data.entity.Board;
import takBoard.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@Tag(name = "Board", description = "Board management APIs")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    @Operation(summary = "Home", description = "Home page")
    public String home() {
        return "home";
    }

    @GetMapping("/read")
    @Operation(summary = "Get Board", description = "Get board by number")
    public ResponseEntity<BoardResponseDTO> getBoard(@RequestParam("number") Long number) {
        BoardResponseDTO boardResponseDTO = boardService.getBoard(number);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
    }

    @GetMapping("/create")
    @Operation(summary = "Create Page", description = "Page for creating a new board")
    public String createPage() {
        return "create";
    }

    @GetMapping("/list")
    @Operation(summary = "Read List", description = "Read list of boards")
    public String readList(Model model) {
        List<Board> members = boardService.findAll();
        model.addAttribute("members", members);
        return "list";
    }

    @GetMapping("/listDetail")
    @Operation(summary = "Read List Detail", description = "Read detailed list of boards")
    public String readListDetail(Model model) {
        List<Board> listMembers = boardService.findAll();
        model.addAttribute("listMembers", listMembers);
        return "listDetail";
    }

    @GetMapping("/detail")
    @Operation(summary = "Read Detail", description = "Read detailed information of a board")
    public String readDetail(@RequestParam("number") Long number, Model model) {
        Optional<Board> detailBoard = boardService.findById(number);
        model.addAttribute("detailBoard", detailBoard);
        return "detail";
    }

    @PostMapping("/create")
    @Operation(summary = "Create Board", description = "Create a new board")
    public String createBoard(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.saveBoard(boardDTO);
        return "redirect:/board";
    }

    @PostMapping("/update")
    @Operation(summary = "Update Board", description = "Update an existing board")
    public String changeBoard(@RequestParam("number") Long number,
                              @RequestParam("title") String title,
                              @RequestParam("context") String context,
                              @RequestParam(value = "photo", required = false) MultipartFile photo) throws Exception {
        ChangeBoardDto changeBoardDto = new ChangeBoardDto();
        changeBoardDto.setNumber(number);
        changeBoardDto.setTitle(title);
        changeBoardDto.setContext(context);
        changeBoardDto.setPhotoUrl(photo);

        boardService.changeBoard(changeBoardDto);
        return "redirect:/board/detail?number=" + number;
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete Board", description = "Delete a board")
    public String deleteBoard(@RequestParam("number") Long number) throws Exception {
        boardService.deleteBoard(number);
        return "redirect:/board";
    }
}
