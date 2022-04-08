package goldra9.board.service;

import goldra9.board.entity.Board;
import goldra9.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService
{

    private final BoardRepository boardRepository;
    public void write(Board board, MultipartFile file) throws Exception {

//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//
//        UUID uuid = UUID.randomUUID();
//
//        String fileName = uuid + "_" + file.getOriginalFilename();
//
//        File saveFile = new File(projectPath, fileName);
//
//        file.transferTo(saveFile);
//
//        board.setFileName(fileName);
//        board.setFilePath("/files/" + fileName);

        boardRepository.save(board);
    }

    public List<Board> boardList()
    {
        return boardRepository.findAll();
    }

    public Board boardView(Integer id)
    {

        return boardRepository.findById(id).get();
    }

    public void delete(Integer id)
    {
        boardRepository.deleteById(id);
    }

}
