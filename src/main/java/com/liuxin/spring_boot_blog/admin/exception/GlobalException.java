package com.liuxin.spring_boot_blog.admin.exception;

import lombok.Getter;
import lombok.Setter;

//  Exception：在程序中必须使用try...catch进行处理。
//  RuntimeException：可以不使用try...catch进行处理，但是如果有异常产生，则异常将由JVM进行处理。
public class GlobalException extends RuntimeException {

  @Getter
  @Setter
  private String msg;

  public GlobalException(String message) {
    this.msg = message;
  }
}
