package com.liuxin.spring_boot_blog.admin.mapper;

import com.liuxin.spring_boot_blog.admin.config.MyMapper;
import com.liuxin.spring_boot_blog.admin.entity.Setting;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuxin
 * @date 2019/6/20 11:41
 * @desc
 */
@Mapper
public interface SettingMapper extends MyMapper<Setting> {
}
