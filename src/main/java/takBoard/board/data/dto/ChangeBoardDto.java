package takBoard.board.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeBoardDto {

    private Long number;

    private String title;

    private String context;

    private MultipartFile photoUrl;


}
