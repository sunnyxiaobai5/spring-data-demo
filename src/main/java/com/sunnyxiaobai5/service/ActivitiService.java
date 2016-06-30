/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: ActivitiService.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

public interface ActivitiService {

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

    /**
     * 领取任务（Task）
     *
     * @param task   流程Task
     * @param userId 领取人ID
     */
    void claimTask(Task task, String userId);

    /**
     * 查询某人领取的任务列表（Task List）
     *
     * @param assignee 用户ID
     * @return List<Task>
     */
    List<Task> getTasks(String assignee);

    /**
     * 完成任务（Task）
     *
     * @param task 流程Task
     */
    void completeTask(Task task);

    /**
     * 查询流程历史实例
     *
     * @param processInstanceId 流程实例ID
     * @return HistoricProcessInstance
     */
    HistoricProcessInstance getHistoryProcessInstance(String processInstanceId);
}
