package com.liuxin.spring_boot_blog.admin.service.impl;

import com.liuxin.spring_boot_blog.admin.entity.Setting;
import com.liuxin.spring_boot_blog.admin.mapper.SettingMapper;
import com.liuxin.spring_boot_blog.admin.utils.PasswordHelper;
import com.liuxin.spring_boot_blog.admin.entity.User;
import com.liuxin.spring_boot_blog.admin.exception.GlobalException;
import com.liuxin.spring_boot_blog.admin.mapper.UserMapper;
import com.liuxin.spring_boot_blog.admin.service.UserService;
import com.liuxin.spring_boot_blog.common.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuxin
 * @date 2019/6/20 11:36
 * @desc
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        try {
            passwordHelper.encryptPassword(user); //加密
            userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        if (user.getId() != 0) {
            try {
                if (user.getPassword() != null && !"".equals(user.getPassword())) {
                    passwordHelper.encryptPassword(user); //加密
                }
                this.updateNotNull(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                this.batchDelete(ids, "id", User.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public User findByName(String username) {
        if (!username.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            List<User> users = userMapper.select(user);
            if (users.size() == 0) {
                return null;
            } else {
                return users.get(0);
            }
        } else {
            return new User();
        }
    }

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public Setting findSetting() {
        return settingMapper.selectAll().get(0);
    }

    @Override
    @Transactional
    public void updateSetting(Setting setting) {
        settingMapper.updateByPrimaryKeySelective(setting);
    }
}