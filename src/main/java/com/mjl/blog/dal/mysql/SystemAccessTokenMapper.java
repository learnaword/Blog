package com.mjl.blog.dal.mysql;

import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemAccessTokenMapper extends BaseMapperX<SystemAccessTokenDO> {
}
