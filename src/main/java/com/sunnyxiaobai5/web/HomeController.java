/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web.home</li>
 * <li>文件名称: Xiangyong Zeng</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web;

import com.sunnyxiaobai5.domain.auth.User;
import com.sunnyxiaobai5.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * Project: spring-data-demo
 * Package: com.sunnyxiaobai5.web.home
 * Title:   描述
 * Author:  Xiangyong Zeng
 * Date:    2015/10/12 16:11
 * Version: 1.0
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/homePage")
    String homePage() {
        return "/index.html";
    }

    @RequestMapping("/homeInfo")
    @ResponseBody
    String homeInfo() {
        return "home info String";
    }


    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "hello home";
    }

    @RequestMapping("/getByName")
    @ResponseBody
    public String getByName(String name) {
        String userId;
        User user = userRepository.findByName(name);
        if (!StringUtils.isEmpty(user)) {
            userId = String.valueOf(user.getId());
            return "this user id is " + userId;
        }
        return "not exists";
    }

    /**
     * pdf预览下载
     *
     * @param fileName  预览文件路径
     * @param request
     * @param response
     * @param isPreview 预览/下载 true：预览，false：下载
     * @param fname
     * @throws IOException
     */
    @RequestMapping("/previewPdf")
    public void previewPdf(String fileName, HttpServletRequest request, HttpServletResponse response,
                           boolean isPreview, String fname) throws IOException {

        fileName = "1.pdf";
        //文件路径，需要动态获取
        String filePath = "D:\\02-workspace\\spring-data-demo\\target\\classes\\" + fileName;

        //是否在线预览 true：在线预览，false：下载
        isPreview = true;

        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] bs = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要

        if (isPreview) {
            URL u = new URL("file:///" + filePath);
            String contentType = u.openConnection().getContentType();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "inline;filename=" + fname);
        } else {
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + fname);
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(bs)) > 0) {
            out.write(bs, 0, len);
        }
        out.flush();
        out.close();
        br.close();
    }

}
