package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.blog.vo.*;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TechnologyBlogAdminConvert {
    TechnologyBlogAdminConvert INSTANCE = Mappers.getMapper(TechnologyBlogAdminConvert.class);

    PageResult<BlogTableRespVO> convert(PageResult<TechnologyBlogDO> blogList);

    TechnologyBlogDO convert(CreateReqVO createReqVO);

    BlogRespVO convert(TechnologyBlogDO blogById);

    TechnologyBlogDO convert(UpdateReqVO updateReqVO);
}
