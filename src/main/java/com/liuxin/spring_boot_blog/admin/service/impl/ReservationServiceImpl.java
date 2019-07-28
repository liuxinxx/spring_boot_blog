package com.liuxin.spring_boot_blog.admin.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuxin.spring_boot_blog.admin.entity.ResLogin;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.service.ResLoginService;
import com.liuxin.spring_boot_blog.admin.service.ReservationService;
import com.liuxin.spring_boot_blog.admin.utils.ReservationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j

@Service
@SuppressWarnings("all")
public class ReservationServiceImpl implements ReservationService {
  @Autowired
  private ResLoginService resLoginService;

  /**
   * 刷新 token
   *
   * @return
   */
  public String findTokne() {

    return login();
  }

  /**
   * 获取座位区域
   */
  @Override
  public String getAllArea() {
    return "getAllArea";
  }

  /**
   * 获取区域的所有座位
   */
  @Override
  public String getAllSeat() {
    return "getAllSeat";
  }

  /**
   * 登录并返回token
   *
   * @return
   */
  @Override
  public String login() {
    ReservationUtil util = new ReservationUtil();
    Map<String, String> params = new HashMap<>();
    params.put("username", "201626030328");
    params.put("password", "201626030328");
    params.put("openid", "o-WiZ5VOtUpPGrFxGrPLYlDLweEU");
    params.put("only", "4587682704");

    Map<String, String> headers = new HashMap<>();
    JSONObject obj = util.post(params, headers);

    if (obj != null) {
      JSONObject data = JSON.parseObject(obj.getString("data"));
      String token = data.getString("token");
      String expire = data.getString("expire");
      ResLogin resLogin = new ResLogin();
      resLogin.setExpire(expire);
      resLogin.setToken(token);
      resLogin.setCreateTime(new Date());
      resLoginService.save(resLogin);
      log.info("===== 登录成功:" + obj.toString());
      return token;
    } else {
      throw new GlobalException("login failure");
    }
  }
}
