package takBoard.board.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import takBoard.board.data.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
// 엔티티가 생성한 데이터베이스에 접근
}