package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.type.vo.*;
import com.mjl.blog.dal.dataobject.TypeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TypeAdminConvert {
    TypeAdminConvert INSTANCE = Mappers.getMapper(TypeAdminConvert.class);

    TypeDO convert(CreateReqVO createReqVO);

    TypeDO convert(UpdateReqVO updateReqVO);

    GetRespVO convert2(TypeDO byId);

    PageResult<TableRespVO> convert(PageResult<TypeDO> list);

    List<TypeListVO> convert(List<TypeDO> list);
}
