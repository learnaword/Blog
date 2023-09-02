package com.mjl.blog.service.admin.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.controller.admin.blog.vo.BlogDateCountsRespVO;
import com.mjl.blog.controller.admin.blog.vo.BlogTypeCountsRespVO;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.mysql.BlogMapper;
import com.mjl.blog.enums.BlogStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogAdminServiceImpl implements BlogAdminService {
    @Resource
    BlogMapper blogMapper;
    @Override
    public BlogTypeCountsRespVO getArticleTypeCounts() {
        BlogTypeCountsRespVO blogTypeCountsRespVO = new BlogTypeCountsRespVO()
                .setDraftCounts(blogMapper.selectCount(BlogDO::getStatus, BlogStatusEnum.DRAFT.getStatus()))
                .setResercedCounts(blogMapper.selectCount(BlogDO::getStatus,BlogStatusEnum.RESERVED.getStatus()))
                .setPublishedCounts(blogMapper.selectCount(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()))
                .setSpmCounts(blogMapper.selectCount(BlogDO::getStatus,BlogStatusEnum.SPAM.getStatus()));
        //文章的总数量
        blogTypeCountsRespVO.initAllCounts();
        return blogTypeCountsRespVO;
    }

    @Override
    public BlogDateCountsRespVO getBlogDateCounts(int preNum) {
        BlogDateCountsRespVO blogDateCountsRespVO = new BlogDateCountsRespVO();
        LocalDate today = LocalDate.now();
        List<Long> BlogDateCounts = new ArrayList<Long>();
        for (int i = 0; i < preNum; i++) {
            Long preDate = today.minusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long nowDate = today.minusDays(i).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();;
            Long sum = blogMapper.selectCount(new LambdaQueryWrapper<BlogDO>()
                    .between(BlogDO::getCreatetime,preDate,nowDate)
                    .eq(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()));
            BlogDateCounts.add(sum);
        }
        blogDateCountsRespVO.setBlogDateCounts(BlogDateCounts);
        return blogDateCountsRespVO;
    }
}
