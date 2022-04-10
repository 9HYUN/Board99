package goldra9.board.controller;

import goldra9.board.entity.Board;
import goldra9.board.repository.BoardRepository;
import goldra9.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController
{
    private final BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm()
    {
        return "board/boardWrite";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board,Model model) {

        boardService.write(board);
        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(size = 2, sort = "id",
                                    direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String searchKeyword)
    {

        Page<Board> list = boardService.boardSearchList(searchKeyword, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage;
        int endPage;
        if(nowPage >= list.getTotalPages() - 2)
        {
            startPage = Math.max(list.getTotalPages() - 4,1);
        } else {
            startPage = Math.max(1, list.getPageable().getPageNumber() - 1);
        }

        if(nowPage <= 3)
        {
            endPage = 5;
            
        } else {
            endPage = Math.min(list.getTotalPages(), nowPage + 2);
        }


        model.addAttribute("list", list);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id)
    {

//        boardService.addCount(id);
        model.addAttribute("board", boardService.boardView(id));
        return "board/boardView";
    }



    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model)
    {

        model.addAttribute("board", boardService.boardView(id));
        return "board/boardUpdate";
    }



    @PostMapping("/board/update/{id}")
    public String update(@PathVariable("id") Integer id, Board board, Model model) throws Exception {
        Board boardTemp = boardService.boardView(id); //기존객체 가져오기
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp);
        model.addAttribute("message","글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    @PostMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable("id") Integer id, Model model)
    {
        boardService.delete(id);
        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }

}
