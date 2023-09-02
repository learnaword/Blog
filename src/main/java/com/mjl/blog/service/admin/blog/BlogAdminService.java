package com.mjl.blog.service.admin.blog;

import com.mjl.blog.controller.admin.blog.vo.BlogDateCountsRespVO;
import com.mjl.blog.controller.admin.blog.vo.BlogTypeCountsRespVO;

public interface BlogAdminService {
    BlogTypeCountsRespVO getArticleTypeCounts();

    BlogDateCountsRespVO getBlogDateCounts(int preNum);
}
