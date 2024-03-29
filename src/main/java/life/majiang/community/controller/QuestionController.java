package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *@Author: easy-fire
 *@Description : 问题接口相关
 *@Date: 2019/8/16
 *@Medified By:
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /**
     * @author : easy-fire
     * @Desicription :问题详情
     * @param :
     * @date : 2019/8/16  11:20
     * @return :
     * @modified By:
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        List<CommentDTO> comments = commentService.listByQuestionId(id);

        questionService.incView(id);
        model.addAttribute("comments",comments);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
