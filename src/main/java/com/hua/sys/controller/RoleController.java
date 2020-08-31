package com.hua.sys.controller;


import com.hua.sys.service.RoleService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.ResultObj;
import com.hua.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 加载右边数据
     */
    @RequestMapping("/loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        return roleService.queryAllRole(roleVo);
    }

    /**
     * 添加角色
     * @param roleVo
     * @return
     */
    @RequestMapping("/addRole")
    public ResultObj addRole(RoleVo roleVo){
        try {
            boolean save = roleService.save(roleVo);
            System.out.println(save);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新角色
     * @param roleVo
     * @return
     */
    @RequestMapping("/updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            boolean update = roleService.updateById(roleVo);
            System.out.println(update);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResultObj deleteRole(RoleVo roleVo){
        try {
            roleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("/deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo){
        try {
            roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的json
     */
    @RequestMapping("/initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid){
        return roleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 保存角色和菜单的关系
     */
    @RequestMapping("/saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo){
        try {
            roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

}

