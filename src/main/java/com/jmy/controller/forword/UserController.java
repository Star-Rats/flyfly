package com.jmy.controller.forword;

import com.jmy.entity.Answer;
import com.jmy.entity.Message;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.service.AnswerService;
import com.jmy.service.MessageService;
import com.jmy.service.PutQuestionService;
import com.jmy.utils.CookieUtils;
import com.jmy.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PutQuestionService putQuestionService = null;
    @Autowired
    private AnswerService answerService = null;
    @Autowired
    private MessageService messageService = null;
    @RequestMapping("/{path}.html")
    public String forword(@PathVariable("path") String path, HttpServletRequest req, Model model,Integer page){
        String userJson = CookieUtils.getCookieValue(req, "user", true);
        User user = null;
        if (userJson != null) {
            try {
                user = MapperUtil.MP.readValue(userJson, User.class);
                model.addAttribute("user",user);
                // 获取用户提问列表
                List<PutQuestion> pqList = putQuestionService.getPQListByOnePage(user.getUserId());
                model.addAttribute("onePage", pqList);
                // 获取用户回答列表
                List<Answer> aList = answerService.getAnswerListByOnePage(user.getUserId());
                // 回答时间
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 根据提问id查找提问标题
                for (int i = 0; i < aList.size(); i++) {
                    Integer pqId = aList.get(i).getPqId();
                    String pt = putQuestionService.getPQTitle(pqId);
                    Answer answer = new Answer();
                    answer.setPqTitle(pt);
                    answer.setPqId(pqId);
                    answer.setAnswerDate(aList.get(i).getAnswerDate());
                    answer.setAnswerComment(aList.get(i).getAnswerComment());
                    aList.set(i,answer);
                }
                model.addAttribute("aList",aList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 获取消息列表
       if (user != null){
           List<Message> mList = messageService.getMessageByUserId(user.getUserId(),page);
           model.addAttribute("mList",mList);
       }

        model.addAttribute("prePage1",1);
        model.addAttribute("nextPage1",2);

        return "/user/" + path + ".html";
    }

    // 分页查看message
    @RequestMapping("/getMessage/{page}")
    public String pageMessage(Model model, @PathVariable("page") Integer pageNum, HttpSession session){

        User user = (User) session.getAttribute("user");
        int nowPage1 = 1;

        if (pageNum != null && pageNum > 1) {
            nowPage1 = pageNum;
        }

        model.addAttribute("nowPage1",nowPage1);
        model.addAttribute("prePage1",nowPage1 - 1);
        model.addAttribute("nextPage1",nowPage1 + 1);
        // 获取消息列表
        List<Message> mList = messageService.getMessageByUserId(user.getUserId(),pageNum);
        model.addAttribute("mList",mList);

        return "/user/message";
    }
}
