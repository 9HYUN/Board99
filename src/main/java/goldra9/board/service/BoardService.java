package goldra9.board.service;

import goldra9.board.entity.Board;
import goldra9.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService
{
    private final BoardRepository boardRepository;

    //==글 작성==//
    @Transactional
    public void write(Board board)
    {
        board.createBoard();
        boardRepository.save(board);
    }
    //==리스트==//
//    public List<Board> boardList()
//    {
//        return boardRepository.findAll();
//    }

    //==글 보기==//
    @Transactional
    public Board boardView(Integer id)
    {
        Optional<Board> result = boardRepository.findById(id);
        result.get().addCount();
        return result.get();
    }
    //==글 삭제==//
    @Transactional
    public void delete(Integer id)
    {
            boardRepository.deleteById(id);
    }

    //== 글 리스트 ==//
    public Page<Board> boardList(Pageable pageable)
    {
        return boardRepository.findAll(pageable);
    }
    
    //== 글 검색 ==//
    public  Page<Board> boardSearchList(String searchKeyword, Pageable pageable)
    {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

}
