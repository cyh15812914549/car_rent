package com.hua.sys.service;

import com.hua.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.MenuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-08-12
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询所有菜单返回
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     *
     * 根据用户id查询用户的可用菜单
     */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);


    /**
     * 查询所有菜单
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 根据id删除菜单
     * @param menuVo
     */
    void deleteMenu(MenuVo menuVo);
}
