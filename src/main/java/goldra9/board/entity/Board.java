package goldra9.board.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Data
public class Board
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private LocalDateTime writeDate;
    private int count;

    //== 생성 로직 ==//
    public void createBoard()
    {
        this.writeDate = LocalDateTime.now();
        this.count = 0;
    }

    //== 조회수 ==//
    public void addCount()
    {
        this.count += 1;
    }



}
