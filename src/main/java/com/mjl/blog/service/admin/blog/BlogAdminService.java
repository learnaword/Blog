package com.mjl.blog.service.admin.blog;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.blog.vo.*;
import com.mjl.blog.dal.dataobject.BlogDO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface BlogAdminService {
    BlogTypeCountsRespVO getArticleTypeCounts();

    BlogDateCountsRespVO getBlogDateCounts(int preNum);

    PageResult<BlogDO> getBlogList(BlogTableReqVO blogTableReqVO);

    void updateBlogsStatus(UpdateBlogsStatusReqVO updateBlogsStatusReqVO);

    void updateBlogsTop(UpdateBlogsTopReqVO updateBlogsTopReqVO);

    void updateBlogsRecommend(UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO);

    int create(CreateReqVO createReqVO);
}
