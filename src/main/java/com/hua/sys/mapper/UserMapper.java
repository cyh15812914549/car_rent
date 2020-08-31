package com.hua.sys.mapper;

import com.hua.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyh
 * @since 2020-08-24
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 登陆
     */
    User login(User user);

    /**
     * 查询用户
     * @param user
     * @return
     */
    List<User> queryAllUser(User user);

    /**
     * 保存用户和角色的关系
     */
    void insertUserRole(@Param("uid")Integer userid,@Param("rid")Integer rid);
}
