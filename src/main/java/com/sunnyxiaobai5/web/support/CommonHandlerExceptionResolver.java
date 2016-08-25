package com.sunnyxiaobai5.web.support;

import com.sunnyxiaobai5.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CommonHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        //TODO 错误日志
        //TODO 判断是否是异步请求，需区别处理
        //TODO 可将错误信息发送邮件给管理员

        if (e instanceof BaseException) {
            handleSystemException(response, e);
        }
        return new ModelAndView("500.html");

    }

    private void handleSystemException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.addIntHeader("code", ((BaseException) e).getCode());
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            printWriter.close();
        } catch (IOException e1) {
            //TODO 异常处理
            e1.printStackTrace();
        }
    }
}
