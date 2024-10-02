package com.mjl.blog.service.admin.technology.blog;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.blog.vo.*;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;

import java.util.List;

public interface TechnologyBlogAdminService {
    BlogTypeCountsRespVO getArticleTypeCounts();

    BlogDateCountsRespVO getBlogDateCounts(int preNum);

    PageResult<TechnologyBlogDO> getBlogList(BlogTableReqVO blogTableReqVO);

    void updateBlogsStatus(UpdateBlogsStatusReqVO updateBlogsStatusReqVO);

    void updateBlogsTop(UpdateBlogsTopReqVO updateBlogsTopReqVO);

    void updateBlogsRecommend(UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO);

    int create(CreateReqVO createReqVO);

    TechnologyBlogDO getBlogById(Long id);

    void update(UpdateReqVO updateReqVO);

    void replace(String name, String name1);

    DataRespVO getEcharsBlogData(Long start, Long end);

}
