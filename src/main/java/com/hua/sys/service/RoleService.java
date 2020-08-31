package com.hua.sys.service;

import com.hua.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.RoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-08-19
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询所有角色返回
     */
    List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     *
     * 根据用户id查询用户的可用角色
     */
    List<Role> queryRoleByUserIdForList(RoleVo roleVo,Integer userId);


    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 批量删除角色
     */
    void deleteBatchRole(Integer[] ids);

    /**
     * 加载角色管理分配菜单的json
     */
    DataGridView initRoleMenuTreeJson(Integer roleid);

    void saveRoleMenu(RoleVo roleVo);
}
