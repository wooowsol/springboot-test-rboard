package org.zerock.rboard.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.rboard.dto.BoardDTO;
import org.zerock.rboard.dto.PageRequestDTO;
import org.zerock.rboard.service.BoardService;

@Controller
@RequestMapping("/rboard")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/")
    public String index(){
        return "redirect:/rboard/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list........" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    //------------------------------------------------------------
    @GetMapping("/register")
    public void register(){
        log.info("register get.....");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..."+ dto);

        //새로 추가된 엔티티의 번호
        Long gno = service.register(dto);

        log.info("GNO: " + gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/rboard/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("gno: " + gno);

        BoardDTO dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){

        log.info("gno: "+ gno);

        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/rboard/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){

        log.info("post modify................................");
        log.info("dto: " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/rboard/read";
    }

}
