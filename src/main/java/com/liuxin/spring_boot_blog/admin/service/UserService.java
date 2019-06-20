package com.liuxin.spring_boot_blog.admin.service;

import com.liuxin.spring_boot_blog.admin.entity.User;
import com.liuxin.spring_boot_blog.admin.entity.Setting;
import com.liuxin.spring_boot_blog.common.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User> {
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据Name查询用户数据
     *
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 更新
     *
     * @param user
     */
    void update(User user);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

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
