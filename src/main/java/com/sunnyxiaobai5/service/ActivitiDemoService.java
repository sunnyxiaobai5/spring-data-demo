/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: ActivitiDemoService.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

public interface ActivitiDemoService {

    /**
     * 根据xml文件部署流程
     *
     * @param resource xml文件路径
     * @return
     */
    Deployment deploy(String resource);

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     */
    ProcessInstance startInstanceByKey(String processDefinitionKey);

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @param businessKey          a key that uniquely identifies the process instance in the context
     *                             or the given process definition.
     * @return
     */
    ProcessInstance startInstanceByKey(String processDefinitionKey, String businessKey);

    /**
     * 根据流程定义key和流程变量启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @param variables            流程变量
     * @return
     */
    ProcessInstance startInstanceByKey(String processDefinitionKey, Map<String, Object> variables);

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @param businessKey          a key that uniquely identifies the process instance in the context
     *                             or the given process definition.
     * @param variables            流程变量
     * @return
     */
    ProcessInstance startInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables);

    List<Task> getTasks(String assignee);
}
