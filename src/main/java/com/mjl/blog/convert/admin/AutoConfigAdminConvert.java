package com.mjl.blog.convert.admin;

import com.mjl.blog.controller.admin.auto.config.vo.CreateReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.GetRespVO;
import com.mjl.blog.controller.admin.auto.config.vo.TableRespVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateReqVO;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoConfigAdminConvert {
    AutoConfigAdminConvert INSTANCE = Mappers.getMapper(AutoConfigAdminConvert.class);

    AutoConfigDO convert(CreateReqVO createReqVO);

    TableRespVO convert(AutoConfigDO autoConfigDO);

    GetRespVO convert2(AutoConfigDO autoConfigById);

    AutoConfigDO convert(UpdateReqVO updateReqVO);
}
