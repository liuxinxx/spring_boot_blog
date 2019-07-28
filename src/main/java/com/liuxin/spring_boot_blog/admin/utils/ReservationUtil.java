package com.liuxin.spring_boot_blog.admin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.Map;

public class ReservationUtil {

  private static String baseUrl;

  public ReservationUtil(String baseUrl) {
    this.baseUrl = baseUrl;
  }
  public ReservationUtil() {

  }

  /**
   * @param params  请求参数
   * @param headers 请求头
   * @return 响应
   */
  public JSONObject post(Map<String, String> params, Map<String, String> headers) {
    // 链接地址
    String praiseUrl = "https://wplib.haut.edu.cn/seatbook/api/seatbook/bindinguser";
    HttpClient client = new HttpClient();
    PostMethod postMethod = new PostMethod(praiseUrl);
    // 必须设置下面这个Header
    for (Map.Entry<String, String> header : headers.entrySet()) {
      postMethod.addRequestHeader(header.getKey(), header.getValue()); // 评论的id，抓包获得
    }
    // 设置参数
    for (Map.Entry<String, String> param : params.entrySet()) {
      postMethod.addParameter(param.getKey(), param.getValue()); // 评论的id，抓包获得
    }

    try {
      int code = client.executeMethod(postMethod);
      if (code == 200) {

        return JSON.parseObject(postMethod.getResponseBodyAsString());
      }else{
        return null;
      }

    } catch (IOException e) {
      e.printStackTrace();
      throw new GlobalException("util: login failure");
    }
  }

//  public static void main(String[] args) {
//
//    System.out.println("-----------分割线------------");
//    Map<String,String> params = new HashMap<>() ;
//    params.put("username","201626030328");
//    params.put("password","201626030328");
//    params.put("openid","o-WiZ5VOtUpPGrFxGrPLYlDLweEU");
//    params.put("only","4587682704");
//
//    Map<String,String> headers = new HashMap<>();
//
//    System.out.println(JSON.parse(post(params,headers)));
//  }

}
