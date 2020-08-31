package com.hua.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.sys.entity.Menu;
import com.hua.sys.mapper.MenuMapper;
import com.hua.sys.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-08-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }

    //根据用户id查询菜单
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return menuMapper.queryMenuByUid(menuVo.getAvailable(),userId);
    }

    public DataGridView queryAllMenu(MenuVo menuVo) {
        System.out.println(menuVo.getPage()+"Page");
        System.out.println(menuVo.getLimit()+"Limit");
        IPage<Menu> menuIPage = new Page<Menu>(menuVo.getPage(),menuVo.getLimit());
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<Menu>();
        menuQueryWrapper.like(StringUtils.isNotEmpty(menuVo.getTitle()),"title",menuVo.getTitle());
        if (menuVo.getId() !=null){
            menuQueryWrapper.and(i->i.eq("id",menuVo.getId()).or().eq("pid",menuVo.getId()));
        }
        menuIPage = menuMapper.selectPage(menuIPage,menuQueryWrapper);
        long total = menuIPage.getTotal();
        List<Menu> list = menuIPage.getRecords();
        return new DataGridView(total,list);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        menuMapper.deleteById(menuVo.getId());
        menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }
}
