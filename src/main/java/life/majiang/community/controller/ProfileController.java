package life.majiang.community.controller;

import life.majiang.community.dto.PageDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author: easy-fire
 *@Description : 下拉框页面跳转相关接口
 *@Date: 2019/8/16
 *@Medified By:
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    /**
     * @author : easy-fire
     * @Desicription :个人相关分类跳转接口
     * @param action:对应页面
     * @date : 2019/8/16  11:01
     * @return :
     * @modified By:
     */
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action")String action, Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "3") Integer size
            ){

        User user = (User) request.getSession().getAttribute("user");

        if(user ==null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PageDTO pageDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pageDTO",pageDTO);
        return "profile";
    }
}
