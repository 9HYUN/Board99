package goldra9.board.controller;

import goldra9.board.entity.Board;
import goldra9.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String boardList(Model model)
    {
        model.addAttribute("list", boardService.boardList());
        return "board/boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id)
    {
        boardService.boardView(id).addCount(); //조회수 안나오냐

        model.addAttribute("board", boardService.boardView(id));
        return "board/boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id)
    {
        boardService.delete(id);
        return "redirect:/board/list";
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

}
