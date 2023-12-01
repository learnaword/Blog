package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.recommend.vo.*;
import com.mjl.blog.dal.dataobject.RecommendDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecommendAdminConvert {
    RecommendAdminConvert INSTANCE = Mappers.getMapper(RecommendAdminConvert.class);


    PageResult<TableRespVO> convert(PageResult<RecommendDO> list);

    RecommendDO convert(CreateReqVO createReqVO);

    RecommendDO convert(UpdateReqVO updateReqVO);

    GetRespVO convert2(RecommendDO byId);

    TableRespVO convert3(RecommendDO item);

    List<RecommendListVO> convert(List<RecommendDO> recommendList);
}
