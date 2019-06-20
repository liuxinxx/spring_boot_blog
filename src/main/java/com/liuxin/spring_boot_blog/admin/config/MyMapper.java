package com.liuxin.spring_boot_blog.admin.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author liuxin
 * @date 2019/6/20 11:42
 * @desc
 */

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
