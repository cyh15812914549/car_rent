package com.hua.sys.mapper;

import com.hua.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyh
 * @since 2020-08-19
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 查询所有角色
     */
    List<Role> queryAllRole(Role role);

    /**
     * 根据角色id删除sys_role_menu里面的数据
     * @param roleId
     */
    void deleteRoleMenuByRid(Integer roleId);

    /**
     * 根据角色id删除sys_role_user里面的数据
     * @param roleId
     */
    void deleteRoleUserByRid(Integer roleId);

    /**
     * 根据用户id删除sys_role_user里面的数据
     * @param roleId
     */
    void deleteRoleUserByUid(Serializable roleId);

    /**
     * 保存角色和菜单的关系sys_role_menu
     */
    void insertRoleMenu(@Param("rid")Integer rid,@Param("mid")Integer mid);

    /**
     * 根据用户id查询角色
     */
    List<Role> queryRoleByUid(@Param("available")Integer available,@Param("uid")Integer userid);

}
