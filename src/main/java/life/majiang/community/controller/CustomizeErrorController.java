package life.majiang.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  Model model){
        HttpStatus status = getStatus(request);

        /**
         * 状态码信息
         * 1xx状态码 临时的响应
         * 2xx状态码 服务器成功接收客户端请求
         * 3xx状态码 客户端必须采取更多操作来实现请求
         * 4xx状态码 发生错误，客户端
         * 5xx状态码 服务器遇到错误而不能完成该请求
         */
        if(status.is4xxClientError()){
            model.addAttribute("message","请求错误");
        }
        if(status.is5xxServerError()){
            model.addAttribute("message","服务端出错");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try{
            return HttpStatus.valueOf(statusCode);
        }
        catch(Exception ex){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
