package com.liuxin.spring_boot_blog.admin.utils;

import io.github.biezhi.wechat.WeChatBot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeixinBoot implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    WeChatBotUtil.setAssetsDir("assets/");
    WeChatBotUtil.getInstance().start();
  }

  /**
   * 根据好友的昵称
   * @param nickName 好友昵称
   * @param msg 发送消息
   */
  public Boolean sendMsg(String nickName, String msg) {
    WeChatBotUtil helloBot = WeChatBotUtil.getInstance();
    if (null != helloBot) {
      String fromUserName = helloBot.api().getAccountByName(nickName).getUserName();
      return helloBot.sendMsg(fromUserName, msg);
    }
    return false;
  }
}
