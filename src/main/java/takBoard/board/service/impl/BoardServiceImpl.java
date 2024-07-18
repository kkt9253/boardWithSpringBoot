package takBoard.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import takBoard.board.data.dao.BoardDAO;
import takBoard.board.data.dto.BoardDTO;
import takBoard.board.data.dto.BoardResponseDTO;
import takBoard.board.data.dto.ChangeBoardDto;
import takBoard.board.data.entity.Board;
import takBoard.board.service.BoardService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<Board> findAll() {
        return boardDAO.findAll();
    }

    @Override
    public Optional<Board> findById(Long number) {
        return boardDAO.findById(number);
    }

    @Override
    public BoardResponseDTO getBoard(Long number) {
        Board board = boardDAO.selectBoard(number);

        return new BoardResponseDTO(board.getNumber(), board.getTitle(), board.getContext(), board.getPhotoUrl());
    }

    @Override
    public BoardResponseDTO saveBoard(BoardDTO boardDTO) throws IOException {
        // BoardDTO를 Board로 변환
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContext(boardDTO.getContext());

        // 사진 저장 및 URL 설정
        String photoUrl = savePhoto(boardDTO); // 사진 URL 가져옴
        board.setPhotoUrl(photoUrl); // board 엔티티에 URL 저장

        // 날짜 설정
        board.setCreatedTime(LocalDateTime.now());
        board.setUpdatedTime(LocalDateTime.now());

        // 엔티티 저장
        Board savedBoard = boardDAO.insertBoard(board);

        // 저장된 엔티티로 DTO 생성
        return new BoardResponseDTO(savedBoard.getNumber(), savedBoard.getTitle(), savedBoard.getContext(), savedBoard.getPhotoUrl());
    }

    @Override
    public String savePhoto(BoardDTO boardDTO) throws IOException {
        MultipartFile photo = boardDTO.getPhoto(); // BoardDTO에서 파일 가져오기
        if (photo == null || photo.isEmpty()) {
            return null; // 파일이 없으면 null 반환
        }

        // 파일 절대 경로 -> 후에 수정해야 할 듯
        String uploadDir = "C:" + File.separator + "Users" + File.separator + "kim47" + File.separator + "Desktop" + File.separator + "3학년1학기" + File.separator +
                "고급웹프" + File.separator + "spring" + File.separator + "takstargram" + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator;
        // 고유한 파일 이름 생성
        String fileName = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName); // 저장 경로 생성

        //Files.createDirectories(filePath.getParent()); // 디렉토리 생성
        // 파일 저장
        photo.transferTo(filePath.toFile());
        System.out.println("uploadDir : " + uploadDir);
        System.out.println("fileName : " + fileName);
        System.out.println("filePath : " + filePath);

        return fileName; // 파일 URL
    }

    @Override
    public BoardResponseDTO changeBoard(ChangeBoardDto changeBoardDto) throws Exception {
        Board board = boardDAO.selectBoard(changeBoardDto.getNumber());
        if (board == null) {
            throw new Exception("Board not found");
        }

        board.setTitle(changeBoardDto.getTitle());
        board.setContext(changeBoardDto.getContext());

        if (changeBoardDto.getPhotoUrl() != null && !changeBoardDto.getPhotoUrl().isEmpty()) {
            String photoUrl = savePhoto(new BoardDTO(changeBoardDto.getTitle(), changeBoardDto.getContext(), changeBoardDto.getPhotoUrl(), null));
            board.setPhotoUrl(photoUrl);
        }

        board.setUpdatedTime(LocalDateTime.now());

        Board updatedBoard = boardDAO.updateBoard(changeBoardDto.getNumber(), board.getTitle(), board.getContext());
        return new BoardResponseDTO(updatedBoard.getNumber(), updatedBoard.getTitle(), updatedBoard.getContext(), updatedBoard.getPhotoUrl());
    }

    @Override
    public void deleteBoard(Long number) throws Exception {
        boardDAO.deleteBoard(number);
    }
}
