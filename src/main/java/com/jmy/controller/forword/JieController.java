package com.jmy.controller.forword;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmy.domain.Question;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.service.jieService;
import com.jmy.utils.CookieUtils;
import com.jmy.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/jie")
public class JieController {

    @Autowired
    private jieService jieService = null;
    @RequestMapping("/{path}.html")
    public String forword(@PathVariable("path") String path, HttpServletRequest req, Model model){
        String userJson = CookieUtils.getCookieValue(req, "user", true);
        List<PutQuestion> hotRead = jieService.getHotRead();
        model.addAttribute("hotRead",hotRead);
        try {
            User user = MapperUtil.MP.readValue(userJson,User.class);
            model.addAttribute("user",user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 综合展示
        List<Question> qList = jieService.getQuestionByDate();
        model.addAttribute("qList",qList);

        // 本周热议
        List<PutQuestion> hostRead = jieService.getHotRead();
        model.addAttribute("hostRead",hostRead);

        model.addAttribute("prePage",1);
        model.addAttribute("nextPage",2);
        return "/jie/" + path;
    }

    // 分页查看综合区帖子
    @RequestMapping("page/{pageNum}")
    public String questionPage(@PathVariable("pageNum") Integer pageNum, Model model){

        // 本周热议
        List<PutQuestion> hostRead = jieService.getHotRead();
        model.addAttribute("hostRead",hostRead);

        // 分页
        List<Question> qList = jieService.getQuestionByPage(pageNum);
        model.addAttribute("qList",qList);
        // 记录当前页码
        int nowPage = 1;

        if (pageNum != null && pageNum > 1) {
            nowPage = pageNum;
        }

        model.addAttribute("nowPage",nowPage);
        model.addAttribute("prePage",nowPage - 1);
        model.addAttribute("nextPage",nowPage + 1);

        return "/jie/index";
    }
}
