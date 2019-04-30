package com.liuxin.spring_boot_blog.admin.service;

import com.liuxin.spring_boot_blog.admin.entity.LoginLog;
import com.liuxin.spring_boot_blog.common.controller.service.BaseService;

import java.util.List;

public interface LoginLogService extends BaseService<LoginLog> {
  List<LoginLog> findByPage(LoginLog log);

  void delete(List<Long> ids);

  void saveLog(LoginLog log);
}
