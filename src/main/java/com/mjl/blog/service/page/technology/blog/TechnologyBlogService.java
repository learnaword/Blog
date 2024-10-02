package com.mjl.blog.service.page.technology.blog;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.technology.blog.vo.*;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;

import java.util.List;

public interface TechnologyBlogService {
    List<TechnologyBlogDO> getOrderList();

    PageResult<TechnologyBlogDO> getNewList(IndexReqVO indexReqVO);

    PageResult<TechnologyBlogDO> getSoftDetails(SoftDetailsReqVO softDetailsReqVO, Long softId);

    PageResult<TechnologyBlogDO> softDetailTypeBlogs(SoftDetailTypeReqVO softDetailTypeReqVO, Long softId, Integer softSection);

    TechnologyBlogDO selectBlogById(Long id);

    List<RelBlogsRespVO>  getRelBlogs(TechnologyBlogDO blog);

    List<RelBlogsRespVO> getHotBlogs(TechnologyBlogDO blog);

    List<TopBlogsRespVO> getTopBlogs(TechnologyBlogDO blog);

    List<TechnologyBlogDO> getNewBlogs();

    TechnologyBlogDO selectNextBlog(Long id, Long softId);

    TechnologyBlogDO selectPrevBlog(Long id, Long softId);

    List<TechnologyBlogDO> find(String keyboard);
}
