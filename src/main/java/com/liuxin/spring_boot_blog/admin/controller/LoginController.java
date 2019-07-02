package com.liuxin.spring_boot_blog.admin.controller;

import com.liuxin.spring_boot_blog.admin.dto.ResponseCode;
import com.liuxin.spring_boot_blog.admin.entity.LoginLog;
import com.liuxin.spring_boot_blog.admin.enums.StatusEnums;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.LoginLogService;
import com.liuxin.spring_boot_blog.admin.service.UserService;
import com.liuxin.spring_boot_blog.admin.utils.AddressUtil;
import com.liuxin.spring_boot_blog.admin.utils.HttpContextUtil;
import com.liuxin.spring_boot_blog.admin.utils.IPUtil;
import com.liuxin.spring_boot_blog.common.controller.BaseController;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
@Slf4j
@SuppressWarnings("all") // 去掉警告
public class LoginController extends BaseController {
    @GetMapping("/admin")
    /**
     * 跳转到后台页面
     */
    public String admin(Model model) {
        model.addAttribute(this.getCurrentUser());
        return ("admin/index");
    }

    /**
     * 跳转到登录页
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;

    @ResponseBody
    @RequestMapping("/admin/login")
    public ResponseCode login(Model model,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "remember", required = false) String remember
    ) {
        if (username != null && password != null) {

            //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
            Subject subject = getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            // 是否记住登录状态
            if (remember != null) {
                //记住
                token.setRememberMe(remember.equals(true));

            } else {
                token.setRememberMe(false);
            }
            try {
                //登入
                try {
                    subject.login(token);
                } catch (UnknownAccountException e) {
                    log.info(e.getMessage());
                    return new ResponseCode(StatusEnums.ACCOUNT_UNKNOWN);
                } catch (Exception e) {
                    log.info(e.getMessage());
                    return new ResponseCode(StatusEnums.ACCOUNT_ERROR);
                }

                //登陆日志
                LoginLog log = new LoginLog();
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
                //通过工具类获取ip地址
                String ip = IPUtil.getIpAddr(request);
                log.setIp(ip);
                //  获取当前用户name
                log.setUsername(super.getCurrentUser().getUsername());
                //  通过IP定位的jar获取登录地址
                log.setLocation(AddressUtil.getAddress(ip));
                //  登录时间
                log.setCreateTime(new Date());
                //  获取请求标识 UA
                String header = request.getHeader("User-Agent");
                UserAgent userAgent = UserAgent.parseUserAgentString(header);
                //  浏览器类型
                Browser browser = userAgent.getBrowser();
                //  系统
                OperatingSystem operatingSystem = userAgent.getOperatingSystem();
                log.setDevice(browser.getName() + " -- " + operatingSystem.getName());
                loginLogService.saveLog(log);
                model.addAttribute("username", getSubject().getPrincipal());
                Thread.sleep(1000);
                return ResponseCode.success();

            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }

        } else {
            throw new GlobalException("用户名或密码错误");
        }
    }
}
