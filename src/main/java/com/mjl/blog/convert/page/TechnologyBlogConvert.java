package com.mjl.blog.convert.page;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.technology.blog.vo.*;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = DateMapper.class)
public interface TechnologyBlogConvert {
    TechnologyBlogConvert INSTANCE = Mappers.getMapper(TechnologyBlogConvert.class);

    List<OrderListRespVO> covertOrder(List<TechnologyBlogDO> orderList);

    PageResult<NewListRespVO> covertNew(PageResult<TechnologyBlogDO> orderList);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    NewListRespVO convertNew(TechnologyBlogDO blogDO);

    List<RelBlogsRespVO> convertRel(List<TechnologyBlogDO> relBlogs);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    RelBlogsRespVO convertRel(TechnologyBlogDO blogDO);

    List<RelBlogsRespVO> convertHot(List<TechnologyBlogDO> hotBlogs);

    List<TopBlogsRespVO> convertRecommend(List<TechnologyBlogDO> blogRecommends);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    TopBlogsRespVO convertRecommend(TechnologyBlogDO blogDO);

    List<NewBlogsTechRespVO> convertNew(List<TechnologyBlogDO> newBlogs);

    NextAndPreBlogRespVO convertNextAndPre(TechnologyBlogDO selectNextBlog);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    BlogInfoRespVO convert(TechnologyBlogDO blogDO);

    List<ResultRespVO> convertResult(List<TechnologyBlogDO> blogDOS);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    ResultRespVO convertResult(TechnologyBlogDO blogDO);

    List<NewBlogsTechRespVO> covertNewTitle(List<TechnologyBlogDO> newList);
}
