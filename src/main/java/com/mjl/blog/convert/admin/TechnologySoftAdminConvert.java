package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TechnologySoftAdminConvert {
    TechnologySoftAdminConvert INSTANCE = Mappers.getMapper(TechnologySoftAdminConvert.class);
    List<SoftListVO> convert(List<TechnologySoftDO> softDOList);
    PageResult<TableRespVO> convert(PageResult<TechnologySoftDO> list);
    TechnologySoftDO convert(CreateReqVO createReqVO);
    TechnologySoftDO convert(UpdateReqVO updateReqVO);

    GetRespVO convert2(TechnologySoftDO byId);

    TableRespVO convert3(TechnologySoftDO item);
}
