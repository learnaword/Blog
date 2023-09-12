package com.mjl.blog.dal.mysql;

import com.mjl.blog.dal.dataobject.LogDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapperX<LogDO> {
}
