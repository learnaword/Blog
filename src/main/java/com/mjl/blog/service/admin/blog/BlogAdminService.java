package com.mjl.blog.service.admin.blog;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.blog.vo.*;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.dal.dataobject.BlogDO;

import java.util.List;

public interface BlogAdminService {
    BlogTypeCountsRespVO getArticleTypeCounts();

    BlogDateCountsRespVO getBlogDateCounts(int preNum);

    PageResult<BlogDO> getBlogList(BlogTableReqVO blogTableReqVO);

    void updateBlogsStatus(UpdateBlogsStatusReqVO updateBlogsStatusReqVO);

    void updateBlogsTop(UpdateBlogsTopReqVO updateBlogsTopReqVO);

    void updateBlogsRecommend(UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO);

    int create(CreateReqVO createReqVO);

    BlogDO getBlogById(Long id);

    void update(UpdateReqVO updateReqVO);

    void replace(String name, String name1);

    DataRespVO getEcharsBlogData(Long start, Long end);

    void updateBlogs(Long softId, List<Integer> adTypes, List<Integer> adTypes1);
}
