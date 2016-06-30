/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web.rest.resource</li>
 * <li>文件名称: ActivitiResource.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.resource;

import com.sunnyxiaobai5.service.ActivitiService;
import com.sunnyxiaobai5.web.rest.dto.ActivitiTaskDTO;
import org.activiti.engine.task.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activiti")
public class ActivitiResource {

    @Resource
    private ActivitiService activitiService;

    @RequestMapping(value = "/startInstanceByKey", method = RequestMethod.GET)
    public void startInstance(@RequestParam String processDefinitionKey) {
        activitiService.startInstanceByKey(processDefinitionKey);
    }

    @RequestMapping(value = "/startInstanceByKeyAndVariables", method = RequestMethod.GET)
    public void startInstanceByKeyAndVariables(@RequestParam String processDefinitionKey) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", 3);
        variables.put("vacationMotivation", "请假测试");

        activitiService.startInstanceByKey(processDefinitionKey, variables);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActivitiTaskDTO> getTasks(@RequestParam String assignee) {
        List<Task> tasks = activitiService.getTasks(assignee);
        List<ActivitiTaskDTO> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new ActivitiTaskDTO(task.getId(), task.getName()));
        }
        return dtos;
    }
}
