package com.mjl.blog.convert;

import com.mjl.blog.controller.admin.auth.vo.LoginRespVO;
import com.mjl.blog.dal.dataobject.SystemAccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {
    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    LoginRespVO convert(SystemAccessTokenDO systemAccessTokenDO);
}
