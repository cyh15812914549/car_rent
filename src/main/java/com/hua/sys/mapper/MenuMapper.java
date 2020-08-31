package com.hua.sys.mapper;

import com.hua.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyh
 * @since 2020-08-12
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);

    /**
     * 根据菜单id删除sys_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid")Integer mid);

    /**
     * 根据角色ID查询菜单
     */
    List<Menu> queryMenuByRoleId(@Param("available")Integer available,@Param("rid")Integer rid);

    /**
     * 根据用户id查询菜单
     * @param available
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available")Integer available, @Param("uid")Integer userId);
}
