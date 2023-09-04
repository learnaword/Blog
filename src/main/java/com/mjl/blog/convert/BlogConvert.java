package com.mjl.blog.convert;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.blog.vo.BlogRespVO;
import com.mjl.blog.controller.admin.blog.vo.BlogTableRespVO;
import com.mjl.blog.controller.admin.blog.vo.CreateReqVO;
import com.mjl.blog.controller.admin.blog.vo.UpdateReqVO;
import com.mjl.blog.dal.dataobject.BlogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogConvert {
    BlogConvert INSTANCE = Mappers.getMapper(BlogConvert.class);

    PageResult<BlogTableRespVO> convert(PageResult<BlogDO> blogList);

    BlogDO convert(CreateReqVO createReqVO);

    BlogRespVO convert(BlogDO blogById);

    BlogDO convert(UpdateReqVO updateReqVO);
}
