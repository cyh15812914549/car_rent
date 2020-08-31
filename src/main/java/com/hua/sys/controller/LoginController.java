package com.hua.sys.controller;

import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.User;
import com.hua.sys.service.UserService;
import com.hua.sys.utils.WebUtils;
import com.hua.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author cyh
 * @date 2020/7/31 17:27
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登陆页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "system/main/login";
    }


    /**
     * 登陆方法
     */
    @RequestMapping("/login")
    public String login(UserVo userVo, Model model) {
        User user= userService.login(userVo);
        if(null!=user) {
            //放到session
            WebUtils.getHttpSession().setAttribute("user", user);
            //记录登陆日志 向sys_login_log里面插入数据
            return "system/main/index";
        }else {
            model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
            return "system/main/login";
        }

    }

}
