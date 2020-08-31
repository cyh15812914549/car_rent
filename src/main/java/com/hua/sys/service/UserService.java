package com.hua.sys.service;

import com.hua.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyh
 * @since 2020-08-24
 */
public interface UserService extends IService<User> {
    User login(UserVo userVo);

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    DataGridView queryAllUser(UserVo userVo);


    /**
     * 批量删除用户
     * @param
     */
    void deleteBatchUser(Integer[] ids);

    /**
     * 重置密码
     * @param userid
     */
    void resetUserPwd(Integer userid);

    /**
     * 加载用户管理的分配角色的数据
     */
    DataGridView queryUserRole(Integer userid);

    /**
     *保存用户和角色的关系
     */
    void saveUserRole(UserVo userVo);
}
