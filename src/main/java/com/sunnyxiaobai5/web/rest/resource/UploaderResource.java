package com.sunnyxiaobai5.web.rest.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/uploader")
public class UploaderResource {

    /**
     * 文件上传
     *
     * @param file
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(MultipartFile file) throws IOException {
        saveToFS(file);
    }

    private void saveToFS(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        try {
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != in) {
                in.close();
            }
        }
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleException(MaxUploadSizeExceededException ex) {
        System.out.println("=====================" + ex.getClass().getName());
        return ResponseEntity.ok("ok");
    }
}
