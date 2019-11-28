package com.jmy.controller.forword;

import com.jmy.domain.Question;
import com.jmy.entity.PutQuestion;
import com.jmy.entity.User;
import com.jmy.service.IndexService;
import com.jmy.service.SearchService;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService = null;
    @Autowired
    private TransportClient client = null;
    @Autowired
    private SearchService searchService = null;
    @RequestMapping("/index.html")
    public String forword(Model model, String flag, String query, HttpSession session){
        // 综合区
        List<Question> qList = new ArrayList();

        // 根据用户喜好展示最新推荐
        User nowUser = (User) session.getAttribute("user");
        qList = indexService.getUserLikeQuestion(nowUser);

        if ("hot".equals(flag)) {
            qList = indexService.getQuestionByHot();
        }
        // 置顶区
        List<Question> tqList = indexService.top();
        model.addAttribute("tqList",tqList);

        // 回帖周榜
        List<User> auList = indexService.getAnswerUser();
        model.addAttribute("auList",auList);

        // 本周热议
        List<PutQuestion> rList = indexService.getPutQuestionByRead();
        model.addAttribute("rList",rList);

        // 获取搜索功能的数据源
        //  searchService.createIndex();

        // 搜索功能
        if (query != null) {
            qList = searchService.queryIndex(query);
        }
        model.addAttribute("qList",qList);
        return "index";
    }

}
