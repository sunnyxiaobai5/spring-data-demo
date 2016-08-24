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
        if (e instanceof BaseException) {
            handleSystemException(response, e);
        }
        return null;
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
