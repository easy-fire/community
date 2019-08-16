package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author: easy-fire
 *@Description : 评论相关接口
 *@Date: 2019/8/16  
 *@Medified By:
 */
@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    /**
     * @author : easy-fire
     * @Desicription :评论问题接口 Ajax传值，@ResponseBody让返回值为json格式，@RequestBody直接接收json格式字符到对象
     * @param :
     * @date : 2019/8/16  10:14
     * @return :
     * @modified By:
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null){
//            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
//        }

        commentService.insert(commentDTO);

        return ResultDTO.okOf();
    }
}
