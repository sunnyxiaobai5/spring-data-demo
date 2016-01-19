/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 * <p>
 * <li>项目名称: spring-data-demo</li>
 * <li>完整包名: com.sunnyxiaobai5.web</li>
 * <li>文件名称: MenuResource.java</li>
 * <li>内容摘要: </li>
 * <li>内容描述: </li>
 * <li>其他说明: </li>
 * <li>@author Xiangyong Zeng</li>
 ******************************************************************************/
package com.sunnyxiaobai5.web.rest.resource;

import com.itextpdf.text.DocumentException;
import com.sunnyxiaobai5.common.exception.BaseException;
import com.sunnyxiaobai5.service.MenuService;
import com.sunnyxiaobai5.util.PdfTableUtils;
import com.sunnyxiaobai5.web.rest.dto.MenuDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuResource {

    @Resource
    private MenuService menuService;

    /**
     * 查询单个菜单
     *
     * @param id 菜单ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MenuDTO> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(menuService.findOneDTO(id), HttpStatus.OK);
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<MenuDTO>> findAll() {
        return new ResponseEntity<>(menuService.findAllDTO(), HttpStatus.OK);
    }

    /**
     * 查询某系统下菜单
     *
     * @param id 系统ID
     * @return
     */
    @RequestMapping(value = "findByParentId/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MenuDTO>> findByParentId(@PathVariable Long id) {
        return new ResponseEntity<>(menuService.findByParentId(id), HttpStatus.OK);
    }

    /**
     * 查询所有系统
     *
     * @return
     */
    @RequestMapping(value = "findSystem", method = RequestMethod.GET)
    public ResponseEntity<List<MenuDTO>> findSystem() {
        return new ResponseEntity<>(menuService.findSystem(), HttpStatus.OK);
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export() throws BaseException {
        //表格标题
        String title = "表格标题";

        try {
            PdfTableUtils.createPdf("pdf/simple_table.pdf", title, MenuDTO.class, menuService.findAllDTO());
        } catch (BaseException e) {
            //外部异常
            if (e.getStatus() > 10000) {
                throw new BaseException(e.getMessage());
            }
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}