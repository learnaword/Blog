package com.mjl.blog.convert.page;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.blog.vo.*;
import com.mjl.blog.dal.dataobject.BlogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = DateMapper.class)
public interface BlogConvert {
    BlogConvert INSTANCE = Mappers.getMapper(BlogConvert.class);

    List<OrderListRespVO> covertOrder(List<BlogDO> orderList);

    PageResult<NewListRespVO> covertNew(PageResult<BlogDO> orderList);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    NewListRespVO convertNew(BlogDO blogDO);

    List<RelBlogsRespVO> convertRel(List<BlogDO> relBlogs);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    RelBlogsRespVO convertRel(BlogDO blogDO);

    List<RelBlogsRespVO> convertHot(List<BlogDO> hotBlogs);

    List<TopBlogsRespVO> convertRecommend(List<BlogDO> blogRecommends);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    TopBlogsRespVO convertRecommend(BlogDO blogDO);

    List<NewBlogsRespVO> convertNew(List<BlogDO> newBlogs);

    NextAndPreBlogRespVO convertNextAndPre(BlogDO selectNextBlog);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    BlogInfoRespVO convert(BlogDO blogDO);

    List<ResultRespVO> convertResult(List<BlogDO> blogDOS);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    ResultRespVO convertResult(BlogDO blogDO);

}
