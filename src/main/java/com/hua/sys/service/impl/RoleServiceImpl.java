package com.hua.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.Menu;
import com.hua.sys.entity.Role;
import com.hua.sys.mapper.MenuMapper;
import com.hua.sys.mapper.RoleMapper;
import com.hua.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.TreeNode;
import com.hua.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-08-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    public DataGridView queryAllRole(RoleVo roleVo) {
        //分页查询
        IPage<Role> roleIPage = new Page<>(roleVo.getPage(),roleVo.getLimit());
        //构造模糊查询
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<Role>();
        if (StringUtils.isNotEmpty(roleVo.getRolename()) || StringUtils.isNotEmpty(roleVo.getRoledesc())){
            roleQueryWrapper.and(i -> i.like("rolename",roleVo.getRolename())
                    .like("roledesc",roleVo.getRoledesc()));
        }
        if (roleVo.getAvailable() != null){
            roleQueryWrapper.and(i->i.eq("available",roleVo.getAvailable()));
        }
        roleIPage = roleMapper.selectPage(roleIPage,roleQueryWrapper);
        //获取总记录数 roleIPage.getTotal()
        //查询数据roleIPage.getRecords()
        return new DataGridView(roleIPage.getTotal(),roleIPage.getRecords());
    }

    @Override
    public void deleteRole(Integer roleId) {
        //删除角色表的数据
        roleMapper.deleteById(roleId);
        //根据角色id删除sys_role_menu里面的数据
        roleMapper.deleteRoleMenuByRid(roleId);
        //根据角色id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByRid(roleId);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids){
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有的可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE,roleid);
        List<TreeNode> data = new ArrayList<>();

        for (Menu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if (m1.getId() == m2.getId()){
                    checkArr = SysConstast.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id,pid,title,spread,checkArr));
        }
        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo) {
        Integer rid = roleVo.getRoleid();
        Integer mids[] = roleVo.getIds();
        //根据rid删除sys_role_menu里面所有数据
        roleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids){
            roleMapper.insertRoleMenu(rid,mid);
        }
    }
}
