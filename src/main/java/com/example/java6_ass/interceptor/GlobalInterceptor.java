package com.example.java6_ass.interceptor;

import com.example.java6_ass.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService categoryService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

           request.setAttribute("category",categoryService.findAll());

    }
}
