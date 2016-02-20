/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web</li>
 * <li>文件名称: UserResource.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.resource;

import com.sunnyxiaobai5.common.Pageable;
import com.sunnyxiaobai5.service.UserService;
import com.sunnyxiaobai5.web.rest.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Resource
    private UserService userService;

    //增
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    //改
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    //查询所有
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> findAll(@RequestParam(value = "number", required = false) Integer number,
                                                 @RequestParam(value = "size", required = false) Integer size) {
        Page<UserDTO> page = userService.findAllDTO(new Pageable(number, size));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    //查询单个
    //删除单个
}
