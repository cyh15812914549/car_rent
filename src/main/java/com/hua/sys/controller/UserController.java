package com.hua.sys.controller;


import com.hua.sys.service.UserService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.ResultObj;
import com.hua.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 加载数据
     */
    @RequestMapping("/loadAllUser")
    public DataGridView loadAllUser(UserVo userVo){
        return userService.queryAllUser(userVo);
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @RequestMapping("/addUser")
    public ResultObj addUser(UserVo userVo){
        try {
            boolean save = userService.save(userVo);
            System.out.println(save);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新用户
     * @param userVo
     * @return
     */
    @RequestMapping("/updateUser")
    public ResultObj updateUser(UserVo userVo){
        try {
            boolean update = userService.updateById(userVo);
            System.out.println(update);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser")
    public ResultObj deleteUser(UserVo userVo){
        try {
            userService.removeById(userVo.getUserid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     */
    @RequestMapping("/deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo){
        try {
            userService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    //重置用户密码
    @RequestMapping("/resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo){
        try {
            userService.resetUserPwd(userVo.getUserid());
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 加载用户管理的分配角色的数据
     */
    @RequestMapping("/initUserRole")
    public DataGridView initUserRole(UserVo userVo){
        return userService.queryUserRole(userVo.getUserid());
    }

    //保存用户和角色的关系
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){
        try {
            userService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}

