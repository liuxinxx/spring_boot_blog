package com.liuxin.spring_boot_blog.admin.controller;

import com.liuxin.spring_boot_blog.admin.service.ReservationService;
import com.liuxin.spring_boot_blog.admin.utils.WeChatBotUtil;
import com.liuxin.spring_boot_blog.admin.utils.WeixinBoot;
import com.liuxin.spring_boot_blog.common.controller.BaseController;
import io.github.biezhi.wechat.api.WeChatApi;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.model.Account;
import io.github.biezhi.wechat.api.model.SyncCheckRet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@SuppressWarnings("all")
@RequestMapping("/api/reservation")
@Slf4j
public class ReservationController extends BaseController {
  @Autowired
  private  ReservationService reservationService;


  @RequestMapping("/show")
  public  String show(){
    return "api/reservation/show";
  }

  @GetMapping(value = "/login")
  public Object  login(){
    log.info(" 登录到预约系统 ");
//    String req = reservationService.login();
    String req = "e63ae453-848e-42e0-8017-7b63e9129e4b";
    Map<String ,String > map = new HashMap<>();
    map.put("status","200");
    map.put("msg","ok");
    map.put("data","");
    System.out.println(req);
    // 登录并发送通知到微信
//    new WeChatBotUtil(Config.me().autoLogin(true)).start();
//    new WeChatBotUtil(Config.me().autoLogin(true)).start();
    WeChatBotUtil weChatBotUtil =  WeChatBotUtil.getInstance();

    log.info("load path:"+ weChatBotUtil.config().assetsDir());
    SyncCheckRet syncCheckRet = weChatBotUtil.api().syncCheck();
    log.info(syncCheckRet.toString());
//    Account account = weChatBotUtil.api().getAccountByName("+++1");
//    log.info(account.toString());
//    boolean s = weChatBotUtil.sendMsg(account.getUserName(),"臭老婆");
//    if (s) {
//      log.info(" 发送成功 ");
//    }else{
//      log.info(" 发送失败 ");
//    }

        log.info("发送通知到文件助手:" + req.toString() );
    try {
      weChatBotUtil.sendMsgToFileHelper("token:"+req.toString());
    }catch (Exception e){
      log.error(e.getMessage());
      e.printStackTrace();

    }

    return req;
  }

  /**
   * 获取所有区域
   * @return
   */
  @GetMapping(value = "/getAllArea")
  public String getAllArea(){
    return reservationService.getAllArea();
  }

  /**
   * 获取区域内所有座位
   * @return
   */
  @GetMapping(value = "/getAllSeat")
  public String getAllSeat(){
    return reservationService.getAllSeat();
  }

  /**
   * 执行
   * @return
   */
  @RequestMapping(value = "/execution")
  public String execution(){
    return "";
  }
}
