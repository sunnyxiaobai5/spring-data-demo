/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: AttachmentServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import com.sunnyxiaobai5.domain.Attachment;
import com.sunnyxiaobai5.repository.AttachmentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
    @Resource
    private AttachmentRepository attachmentRepository;


    @Override
    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public void delete(String id) {
        attachmentRepository.delete(id);
    }
}
