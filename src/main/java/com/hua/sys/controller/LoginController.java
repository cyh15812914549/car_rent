package com.hua.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.hua.sys.constast.SysConstast;
import com.hua.sys.entity.User;
import com.hua.sys.service.LogLoginService;
import com.hua.sys.service.UserService;
import com.hua.sys.utils.WebUtils;
import com.hua.sys.vo.LogLoginVo;
import com.hua.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


/**
 * @author cyh
 * @date 2020/7/31 17:27
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogLoginService logLoginService;

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
        String code = WebUtils.getHttpSession().getAttribute("code").toString();
        if (userVo.getCode().equals(code)){
            User user= userService.login(userVo);
            if(null!=user) {
                //放到session
                WebUtils.getHttpSession().setAttribute("user", user);
                //记录登陆日志 向sys_login_log里面插入数据
                LogLoginVo logLoginVo = new LogLoginVo();
                logLoginVo.setLogintime(new Date());
                logLoginVo.setLoginname(user.getRealname()+"-"+user.getLoginname());
                logLoginVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                logLoginService.addLogLogin(logLoginVo);
                return "system/main/index";
            }else {
                model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        }else {
            model.addAttribute("error", SysConstast.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }
    }

    /**
     * 得到登陆验证码
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,4,5);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }

}
