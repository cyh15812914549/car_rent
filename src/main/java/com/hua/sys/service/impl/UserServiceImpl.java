package com.hua.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.Role;
import com.hua.sys.entity.User;
import com.hua.sys.mapper.RoleMapper;
import com.hua.sys.mapper.UserMapper;
import com.hua.sys.service.UserService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyh
 * @since 2020-08-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public User login(UserVo userVo) {
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        IPage<User> userVoIPage = new Page<>(userVo.getPage(),userVo.getLimit());
        //构造模糊查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("type",1);
        if (StringUtils.isNotEmpty(userVo.getLoginname()) || StringUtils.isNotEmpty(userVo.getRealname()) || StringUtils.isNotEmpty(userVo.getPhone())
            || StringUtils.isNotEmpty(userVo.getIdentity()) || StringUtils.isNotEmpty(userVo.getAddress())){
            queryWrapper.and(i -> i.like("loginname",userVo.getLoginname()).like("address",userVo.getAddress())
                    .like("realname",userVo.getRealname()).like("identity",userVo.getIdentity()).like("phone",userVo.getPhone()));
        }
        if (userVo.getSex() != null){
            queryWrapper.and(i->i.eq("sex",userVo.getSex()));
        }
        userVoIPage = userMapper.selectPage(userVoIPage,queryWrapper);
        return new DataGridView(userVoIPage.getTotal(),userVoIPage.getRecords());
    }

    @Override
    public boolean removeById(Serializable id) {
        // 根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(id);
        return super.removeById(id);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer id : ids) {
            removeById(id);
        }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setUserid(userid);
        // 设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //更新
        userMapper.updateById(user);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1.查询所有可用的角色
        Role role = new Role();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole = roleMapper.queryAllRole(role);
        //2.根据用户ID查询已拥有的角色
        List<Role> userRole = roleMapper.queryRoleByUid(SysConstast.AVAILABLE_TRUE,userid);
        List<Map<String,Object>> data = new ArrayList<>();
        for (Role role1 : allRole) {
            Boolean LAY_CHECKED = false;
            for (Role role2 : userRole) {
                if (role1.getRoleid()==role2.getRoleid()){
                    LAY_CHECKED=true;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("roleid",role1.getRoleid());
            map.put("rolename",role1.getRolename());
            map.put("roledesc",role1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();
        //根据用户id删除sys_role_user里面的数据
        roleMapper.deleteRoleUserByUid(userid);
        //保存用户角色表数据
        if (roleIds!=null&&roleIds.length>0){
            for (Integer roleId : roleIds) {
                userMapper.insertUserRole(userid,roleId);
            }
        }
    }
}
