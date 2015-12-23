/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.service</li>
 * <li>文件名称: ActivitiDemoServiceImpl.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivitiDemoServiceImpl implements ActivitiDemoService {

    Logger Log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    /**
     * 根据xml文件部署流程
     *
     * @param resource xml文件路径
     * @return
     */
    @Override
    public Deployment deploy(String resource) {
        return repositoryService.createDeployment().addClasspathResource(resource).deploy();
    }

    /**
     * 根据流程定义key启动一个最新版本的流程
     *
     * @param processDefinitionKey 流程定义key
     * @return
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
     * @param businessKey          a key that uniquely identifies the process instance in the context
     *                             or the given process definition.
     * @return
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
     * @return
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
     * @param businessKey          a key that uniquely identifies the process instance in the context
     *                             or the given process definition.
     * @param variables            流程变量
     * @return
     */
    @Override
    public ProcessInstance startInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    }

    @Override
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }
}
