package com.mjl.blog.dal.mysql;

import com.mjl.blog.dal.dataobject.AdminUserDO;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapperX<BlogDO> {

    //根据username获取到用户信息
}
