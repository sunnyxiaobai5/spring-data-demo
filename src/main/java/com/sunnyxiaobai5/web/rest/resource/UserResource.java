/*******************************************************************************
 * sunnyxiaobai5@gmail.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web</li>
 * <li>文件名称: UserResource.java</li>
 * <li>内容摘要:</li>
 * <li>内容描述:</li>
 * <li>其他说明:</li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.resource;

import com.sunnyxiaobai5.common.BaseExcelView;
import com.sunnyxiaobai5.domain.auth.User;
import com.sunnyxiaobai5.service.auth.UserService;
import com.sunnyxiaobai5.web.rest.dto.UserDTO;
import com.sunnyxiaobai5.web.rest.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    /**
     * 增加
     *
     * @param userDTO 人员DTO
     * @return ResponseEntity
     * @throws URISyntaxException
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO) throws URISyntaxException {
        if (null != userDTO.getId()) {
            return ResponseEntity.badRequest().header("fail", "新建user不能有ID").build();
        }

        User user = userService.save(userDTO);

        return ResponseEntity.created(new URI("/user/" + user.getId())).build();
    }

    /**
     * 修改
     *
     * @param userDTO 人员DTO
     * @return ResponseEntity
     * @throws URISyntaxException
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) throws URISyntaxException {
        if (null == userDTO.getId()) {
            return create(userDTO);
        }

        userService.save(userDTO);

        return ResponseEntity.ok().build();
    }

    /**
     * 查询所有
     *
     * @param number 页码
     * @param size 每页条数
     * @return ResponseEntity<List<UserDTO>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll(
            @RequestParam(value = "number", required = false) Integer number,
            @RequestParam(value = "size", required = false) Integer size) {
        Page<User> page = userService.findAll(new PageRequest(number, size));

        List<UserDTO> result = userMapper.userToUserDTO(page.getContent());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 查询单个
     *
     * @param id 人员ID
     * @return ResponseEntity<UserDTO>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
        return Optional.ofNullable(userService.findOne(id))
                .map(userMapper::userToUserDTO)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 删除单个
     *
     * @param id 人员ID
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    /**
     * 导出excel
     *
     * @param ids
     */
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exportExcel(Long[] ids) throws Exception {

        Map<String, Object> model = new HashMap<>();

        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("name", "姓名");
        headerMap.put("description", "描述");
        headerMap.put("genderCode", "性别");
        headerMap.put("email", "邮箱");

        List<User> users = userService.findAll(Arrays.asList(ids));
        model.put("title", "导出ExcelDemo");
        model.put("headerMap", headerMap);
        model.put("dataList", users);

        return new ModelAndView(new BaseExcelView(), model);
    }

    /**
     * 查询单个
     *
     * @param key cache key
     * @return ResponseEntity<UserDTO>
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> testCache(String key) {
        return Optional.ofNullable(userService.testCache(key))
                .map(userMapper::userToUserDTO)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
