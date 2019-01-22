package com.yuhao.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 */

@Component
public  class CustomExceptionResolver implements HandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(CustomExceptionResolver.class);


    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,Object o ,Exception ex) {

        //如果是自定义异常，则显示ex.getMessage ，否则显示  系统系统异常，请稍后再试
        String errorMsg = null;
        if (ex instanceof TransforException){
            errorMsg = ex.getMessage();
            log.info(ex.getMessage());
        }else {
            errorMsg = "系统异常，请稍后重试";
            log.info(ex.getMessage(),ex);
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",errorMsg);
        modelAndView.setViewName("exception");

        return modelAndView;
    }
}
