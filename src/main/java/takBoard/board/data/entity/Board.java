package takBoard.board.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String context;

    @Column
    private String photoUrl; // 사진 URL 저장

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}