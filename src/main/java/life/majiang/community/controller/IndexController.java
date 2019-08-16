package life.majiang.community.controller;

import life.majiang.community.dto.PageDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *@Author: easy-fire
 *@Description :  首页数据显示控制
 *@Date: 2019/8/16  
 *@Medified By:
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    /**
     * @author : easy-fire
     * @Desicription :首页跳转，查询所有问题信息，分页显示在首页上
     * @param page:默认从第一页开始显示
     * @param size:默认每页显示3条数据
     * @date : 2019/8/16  8:49
     * @return :
     * @modified By:
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "3") Integer size){

        PageDTO pageDTO = questionService.list(page,size);

        model.addAttribute("pageDTO",pageDTO);
        return "index";
    }
}
