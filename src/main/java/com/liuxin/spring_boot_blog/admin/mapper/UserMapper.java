package com.liuxin.spring_boot_blog.admin.mapper;

import com.liuxin.spring_boot_blog.admin.config.MyMapper;
import com.liuxin.spring_boot_blog.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author liuxin
 * @date 2019/6/20 11:46
 * @desc
 */
public interface UserMapper extends MyMapper<User> {
}
