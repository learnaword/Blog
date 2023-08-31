package com.mjl.blog.dal.mysql;

import com.mjl.blog.dal.dataobject.AdminUserDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper extends BaseMapperX<AdminUserDO> {

    //根据username获取到用户信息
    default AdminUserDO getAdminUserByUsername(String username){
        return selectOne(AdminUserDO::getUsername,username);
    }
}
