/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: ActivitiServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivitiServiceImpl implements ActivitiService {

    private static final Logger Log = LoggerFactory.getLogger(ActivitiServiceImpl.class);

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    /**
     * 根据xml文件部署流程
     *
     * @param resource xml文件路径
     * @return Deployment
     */
    @Override
    public Deployment deploy(String resource) {
        return repositoryService.createDeployment().addClasspathResource(resource).deploy();
    }

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @return 刚启动流程实例
     */
    @Override
    public ProcessInstance startInstanceByKey(String processDefinitionKey) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        Log.info("【Activiti】Start new Instance! Count:" + runtimeService.createProcessInstanceQuery().count());
        return processInstance;
    }

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @param businessKey          在上下文或者给定流程定义中唯一标识流程实例的key
     * @return 刚启动流程实例
     */
    @Override
    public ProcessInstance startInstanceByKey(String processDefinitionKey, String businessKey) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        Log.info("【Activiti】Start new Instance! Count:" + runtimeService.createProcessInstanceQuery().count());
        return processInstance;
    }

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @param variables            流程变量
     * @return 刚启动流程实例
     */
    @Override
    public ProcessInstance startInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        Log.info("【Activiti】Start new Instance! Count:" + runtimeService.createProcessInstanceQuery().count());
        return processInstance;
    }

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @param businessKey          在上下文或者给定流程定义中唯一标识流程实例的key
     * @param variables            流程变量
     * @return 刚启动流程实例
     */
    @Override
    public ProcessInstance startInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        Log.info("【Activiti】Start new Instance! Count:" + runtimeService.createProcessInstanceQuery().count());
        return processInstance;
    }

    /**
     * 领取任务（Task）
     *
     * @param task   流程Task
     * @param userId 领取人ID
     */
    @Override
    public void claimTask(Task task, String userId) {
        taskService.claim(task.getId(), userId);
    }

    /**
     * 查询某人领取的任务列表（Task List）
     *
     * @param assignee 用户ID
     * @return List<Task>
     */
    @Override
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    /**
     * 完成任务（Task）
     *
     * @param task 流程Task
     */
    @Override
    public void completeTask(Task task) {
        taskService.complete(task.getId());
    }

    /**
     * 查询流程历史实例
     *
     * @param processInstanceId 流程实例ID
     * @return HistoricProcessInstance
     */
    @Override
    public HistoricProcessInstance getHistoryProcessInstance(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }
}
