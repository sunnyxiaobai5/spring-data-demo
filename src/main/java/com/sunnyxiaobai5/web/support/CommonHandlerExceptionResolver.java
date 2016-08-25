package com.sunnyxiaobai5.web.support;

import com.alibaba.fastjson.JSON;
import com.sunnyxiaobai5.common.exception.BaseException;
import com.sunnyxiaobai5.common.exception.BussinessException;
import com.sunnyxiaobai5.common.exception.CommonException;
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

//        if (e instanceof CommonException) {
//            handleBaseException(response, e);
//            return new ModelAndView();
//        } else if (e instanceof BussinessException) {
//            handleBaseException(response, e);
//            return new ModelAndView();
//        } else if (e instanceof BaseException) {
//            handleBaseException(response, e);
//            return new ModelAndView();
//        }
//        return new ModelAndView("500.html");
        return null;
    }

    private void handleBaseException(HttpServletResponse response, Exception e) {

        //可以写回一个Object，并且不会暴露后端的异常类型
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.addIntHeader("code", ((BaseException) e).getCode());
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(new ExceptionInfo(((BaseException) e).getCode(), e.getMessage())));
            printWriter.close();
        } catch (IOException e1) {
            //TODO 异常处理
        }

//        //返回信息有限制，并且会暴露异常类型
//        try {
//            response.addIntHeader("code", ((BaseException) e).getCode());
//            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//        } catch (IOException e1) {
//            //TODO 异常处理
//        }

    }

    class ExceptionInfo {
        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private ExceptionInfo(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
