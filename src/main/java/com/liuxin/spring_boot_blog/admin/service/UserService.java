package com.liuxin.spring_boot_blog.admin.service;

import com.liuxin.spring_boot_blog.admin.entity.User;
import com.liuxin.spring_boot_blog.admin.entity.Setting;
import com.liuxin.spring_boot_blog.common.controller.service.BaseService;

import java.util.List;

public interface UserService  extends BaseService {
    /**
     * 查询用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     *  根据name 查找用户
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     *  更新用户
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    User delete(List<Long> ids);

    /**
     * 获取系统设置数据
     *
     * @return
     */
    Setting findSetting();

    /**
     * 更新设置信息
     *
     * @param setting
     */
    void updateSetting(Setting setting);
}
