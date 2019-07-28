package com.liuxin.spring_boot_blog.admin.utils;

import com.liuxin.spring_boot_blog.admin.service.ReservationService;
import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WeChatBotUtil extends WeChatBot {
//  @Autowired
//  private ReservationService reservationService;
//  public WeChatBotUtil(Config config) {
//    super(config);
//  }
//
//  @Bind(msgType = MsgType.TEXT)
//  public void handleText(WeChatMessage message) {
//    if (StringUtils.isNotEmpty(message.getName())) {
//      log.info("接收到 [{}] 的消息: {}", message.getName(), message.getText());
//      this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
//    }
//  }
//
//  public static void main(String[] args) {
//
//    WeChatBotUtil botUtil = new WeChatBotUtil(Config.me());
//
//    Object loginToken = null;
//    botUtil.sendMsgToFileHelper("token:"+loginToken.toString());
//
//  }

  //登陆二维保存路径
  private static String assetsDir = "./QRCodePath/";

  private volatile static WeChatBotUtil helloBot;

  public static void setAssetsDir(String assetsDir) {
    WeChatBotUtil.assetsDir = assetsDir;
  }

  public static WeChatBotUtil getInstance(){
    if(helloBot == null){
      synchronized (WeChatBotUtil.class){
        if(helloBot ==null){
          helloBot = new WeChatBotUtil(Config.me().autoLogin(true).assetsDir(assetsDir).showTerminal(true));
        }
      }
    }
    return helloBot;
  }
  private WeChatBotUtil(Config config) {
    super(config);
  }

  public static void main(String[] args) {
    getInstance().start();
  }
}
