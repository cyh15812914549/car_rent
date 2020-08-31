package com.hua.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.Menu;
import com.hua.sys.entity.User;
import com.hua.sys.service.MenuService;
import com.hua.sys.utils.*;
import com.hua.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyh
 * @since 2020-08-12
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        //得到当前登录的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Menu> list = null;
        //只查询可用的
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        if(user.getType() == SysConstast.USER_TYPE_SUPER){
            list=menuService.queryAllMenuForList(menuVo);
        }else {
            list=menuService.queryMenuByUserIdForList(menuVo,user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return TreeNodeBuilder.builder(nodes,1);
    }

    /**
     * 加载菜单管理左边的菜单树
     */
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> list = menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return new DataGridView(nodes);
    }

    /**
     * 加载右边数据
     */
    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo){
        return menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("/addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try {
            boolean save = menuService.save(menuVo);
            System.out.println(save);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新菜单
     * @param menuVo
     * @return
     */
    @RequestMapping("/updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try {
            boolean update = menuService.updateById(menuVo);
            System.out.println(update);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        //根据pid查询菜单数量
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
        Map<String, Object> menuMap = menuService.getMap(queryWrapper.eq("pid", menuVo.getId()));
        //如果有值就返回STATUS_TRUE
        if (menuMap != null){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo){
        try {
            menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

