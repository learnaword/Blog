package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.type.vo.*;
import com.mjl.blog.dal.dataobject.TechnologyTypeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TechnologyTypeAdminConvert {
    TechnologyTypeAdminConvert INSTANCE = Mappers.getMapper(TechnologyTypeAdminConvert.class);

    TechnologyTypeDO convert(CreateReqVO createReqVO);

    TechnologyTypeDO convert(UpdateReqVO updateReqVO);

    GetRespVO convert2(TechnologyTypeDO byId);

    PageResult<TableRespVO> convert(PageResult<TechnologyTypeDO> list);

    List<TypeListVO> convert(List<TechnologyTypeDO> list);
}
