package com.mjl.blog.convert.page;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface TechnologySoftConvert {
    TechnologySoftConvert INSTANCE = Mappers.getMapper(TechnologySoftConvert.class);

    PageResult<SoftListRespVO> covert(PageResult<TechnologySoftDO> list);

    PageResult<SoftDetailsRespVO> covertDetails(PageResult<TechnologyBlogDO> softDetails);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    SoftDetailsRespVO covertDetails(TechnologyBlogDO blogDO);

    PageResult<SoftInfoRespVO> covertInfo(PageResult<TechnologySoftDO> softInfos);

    PageResult<SoftDetailTypeRespVO> covertDetailType(PageResult<TechnologyBlogDO> softDetailTypeBlogs);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    SoftDetailTypeRespVO covertDetailType(TechnologyBlogDO blogDO);
}
