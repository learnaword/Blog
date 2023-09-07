package com.mjl.blog.service.page.blog;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.blog.vo.IndexReqVO;
import com.mjl.blog.controller.page.blog.vo.TopBlogsRespVO;
import com.mjl.blog.controller.page.blog.vo.RelBlogsRespVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailTypeReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailsReqVO;
import com.mjl.blog.dal.dataobject.BlogDO;

import java.util.List;

public interface BlogService {
    List<BlogDO> getOrderList();

    PageResult<BlogDO> getNewList(IndexReqVO indexReqVO);

    PageResult<BlogDO> getSoftDetails(SoftDetailsReqVO softDetailsReqVO,Long softId);

    PageResult<BlogDO> softDetailTypeBlogs(SoftDetailTypeReqVO softDetailTypeReqVO, Long softId, Integer softSection);

    BlogDO selectBlogById(Long id);

    List<RelBlogsRespVO>  getRelBlogs(BlogDO blog);

    List<RelBlogsRespVO> getHotBlogs(BlogDO blog);

    List<TopBlogsRespVO> getTopBlogs(BlogDO blog);

    List<BlogDO> getNewBlogs(BlogDO blog);

    BlogDO selectNextBlog(Long id, Long softId);

    BlogDO selectPrevBlog(Long id, Long softId);

    List<BlogDO> find(String keyboard);
}
