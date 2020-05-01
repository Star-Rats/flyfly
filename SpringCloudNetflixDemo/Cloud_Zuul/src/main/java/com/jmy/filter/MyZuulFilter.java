package com.jmy.filter;

import com.alibaba.fastjson.JSON;
import com.jmy.cloud.entity.R;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyZuulFilter extends ZuulFilter {

    /**
     * 1.过滤器类型 取值如下：
     * PRE 预处理 前置过滤器
     * ROUTING 处理中 正在执行
     * POST 请求结束 后置过滤
     * ERROR 错误过滤 一般用于收集错误信息
     * */
    @Override
    public String filterType() {
        return "pre";// 使用前置过滤
    }

    // 过滤级别 返回值越小 过滤级别越高（当有其他ZuulFilter的实现类同时存在时）
    @Override
    public int filterOrder() {
        return 0;
    }

    // 是否开启zuul网关的过滤 (我不开你我写你干嘛。。返回值改为true)
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 核心方法 编写过滤细节
    @Override
    public Object run() throws ZuulException {
        System.out.println("前置过滤开始...");
        // 获取请求的上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取请求对象
        HttpServletRequest request = requestContext.getRequest();
        // 过滤掉请求参数不包含name的请求
        if (request.getParameter("name") == null) {
            requestContext.setResponseStatusCode(400);
            requestContext.setSendZuulResponse(false);
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("application/json;charset=UTF-8");

            try {
                response.getWriter().print(JSON.toJSONString(R.fail("参数中必须包含name")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
