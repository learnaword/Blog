package com.mjl.blog.dal.mysql;

import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapperX<FileDO> {
}
