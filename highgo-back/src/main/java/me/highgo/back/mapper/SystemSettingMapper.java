package me.highgo.back.mapper;

import me.highgo.back.model.SystemSetting;

public interface SystemSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemSetting record);

    int insertSelective(SystemSetting record);

    SystemSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemSetting record);

    int updateByPrimaryKey(SystemSetting record);
}