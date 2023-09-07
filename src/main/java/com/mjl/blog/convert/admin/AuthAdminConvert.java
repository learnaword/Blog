package com.mjl.blog.convert.admin;

import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthAdminConvert {
    AuthAdminConvert INSTANCE = Mappers.getMapper(AuthAdminConvert.class);

    LoginRespVO convert(SystemAccessTokenDO systemAccessTokenDO);
}
