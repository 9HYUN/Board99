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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY는 오라클에서 씀 AUTO해도 무방
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime writeDate;
//    private String fileName;
//    private String filePath;

}
