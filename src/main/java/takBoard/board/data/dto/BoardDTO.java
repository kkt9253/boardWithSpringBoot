package takBoard.board.data.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private String title;

    private String context;

    private MultipartFile photo; // 사진 파일

    private String photoUrl; // 사진 URL

}
