package goldra9.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Data
public class Board
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime writeDate;
    private Long count;

    public void createBoard()
    {
        this.writeDate = LocalDateTime.now();
        this.count = 0l;
    }
    public Long addCount()
    {
        this.count += 1 ;
        return count;
    }

//    private String fileName;
//    private String filePath;

}
