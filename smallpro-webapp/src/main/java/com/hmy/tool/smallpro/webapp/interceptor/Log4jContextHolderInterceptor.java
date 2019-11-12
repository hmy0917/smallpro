package com.keep.kit.smallpro.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.keep.user.account.model.User;

/**
 * @author fanxiaodong (fanxiaodong@gotokeep.com)
 * @date 2018/8/31
 * @time 上午11:19
 **/
public class Log4jContextHolderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userObject = request.getAttribute("currentUser");
        if (userObject == null) {
            ThreadContext.put("userId", "");
            return true;
        }

        User user = (User) userObject;
        ThreadContext.put("userId", user.getId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadContext.clearMap();
    }
}

