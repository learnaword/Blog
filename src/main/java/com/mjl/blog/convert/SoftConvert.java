package com.mjl.blog.convert;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.*;
import com.mjl.blog.dal.dataobject.SoftDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SoftConvert {
    SoftConvert INSTANCE = Mappers.getMapper(SoftConvert.class);
    List<SoftListVO> convert(List<SoftDO> softDOList);
    PageResult<TableRespVO> convert(PageResult<SoftDO> list);
    SoftDO convert(CreateReqVO createReqVO);
    SoftDO convert(UpdateReqVO updateReqVO);

    GetRespVO convert2(SoftDO byId);
}
