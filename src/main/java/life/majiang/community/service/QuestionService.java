package life.majiang.community.service;

import life.majiang.community.dto.PageDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 *@Author: easy-fire
 *@Description : 问题相关服务
 *@Date: 2019/8/16  
 *@Medified By:
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @author : easy-fire
     * @Desicription :分页查询所有问题，封装到PageDTO类中
     * @param page:指定页数
     * @param size:显示条数
     * @date : 2019/8/16  8:56
     * @return :
     * @modified By:
     */
    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer count = questionMapper.count();
        pageDTO.setPage(count,page,size);

        Integer offset = size * (pageDTO.getPage()-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;
    }
    /**
     * @author : easy-fire
     * @Desicription :分页查询某人的所有问题信息
     * @param userId:被查询人id
     * @param page:所在页码
     * @param size:每页条数
     * @date : 2019/8/16  8:59
     * @return :
     * @modified By:
     */
    public PageDTO list(Integer userId, Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        Integer count = questionMapper.countByUserId(userId);
        pageDTO.setPage(count,page,size);

        Integer offset = size * (pageDTO.getPage()-1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;
    }

    /**
     * @author : easy-fire
     * @Desicription :
     * @param : 
     * @date : 2019/8/16  9:03
     * @return : 
     * @modified By:
     */
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
           questionMapper.update(question);
        }
    }

    public void incView(Integer id) {
        Question question = questionMapper.getById(id);
        question.setViewCount(question.getViewCount()+1);
        questionMapper.update(question);
    }

    public void incCommentCount(Question question) {
        question.setCommentCount(question.getCommentCount()+1);
        questionMapper.update(question);
    }
}
