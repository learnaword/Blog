package com.mjl.blog.service.admin.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.blog.vo.*;
import com.mjl.blog.convert.BlogConvert;
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
        ArrayList<Long> BlogDateCounts = new ArrayList<Long>();
        for (int i = 0; i < preNum; i++) {
            Long preDate = today.minusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long nowDate = today.minusDays(i).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();;
            Long sum = blogMapper.selectCount(new LambdaQueryWrapper<BlogDO>()
                    .between(BlogDO::getCreateTime,preDate,nowDate)
                    .eq(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()));
            BlogDateCounts.add(sum);
        }
        blogDateCountsRespVO.setBlogDateCounts(BlogDateCounts);
        return blogDateCountsRespVO;
    }

    @Override
    public PageResult<BlogDO> getBlogList(BlogTableReqVO blogTableReqVO) {
        return blogMapper.selectPage(blogTableReqVO);
    }

    @Override
    public void updateBlogsStatus(UpdateBlogsStatusReqVO updateBlogsStatusReqVO) {
        List<BlogDO> blogDOList =  blogMapper.selectList(new LambdaQueryWrapper<BlogDO>().in(BlogDO::getId,updateBlogsStatusReqVO.getIds()));
        blogDOList.forEach(item -> item.setStatus(updateBlogsStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        blogMapper.updateBatch(blogDOList);
    }

    @Override
    public void updateBlogsTop(UpdateBlogsTopReqVO updateBlogsTopReqVO) {
        List<BlogDO> blogDOList =  blogMapper.selectList(new LambdaQueryWrapper<BlogDO>().in(BlogDO::getId,updateBlogsTopReqVO.getIds()));
        blogDOList.forEach(item -> item.setIsTop(updateBlogsTopReqVO.getIsTop()).setUpdateTime(System.currentTimeMillis()));
        blogMapper.updateBatch(blogDOList);
    }

    @Override
    public void updateBlogsRecommend(UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO) {
        List<BlogDO> blogDOList =  blogMapper.selectList(new LambdaQueryWrapper<BlogDO>().in(BlogDO::getId,updateBlogsRecommendReqVO.getIds()));
        blogDOList.forEach(item -> item.setIsRecommend(updateBlogsRecommendReqVO.getIsRecommend()).setUpdateTime(System.currentTimeMillis()));
        blogMapper.updateBatch(blogDOList);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        BlogDO blogDO = BlogConvert.INSTANCE.convert(createReqVO);
        blogDO.setStatus(BlogStatusEnum.PUBLISHED.getStatus());
        blogDO.setCreateTime(System.currentTimeMillis());
        blogDO.setUpdateTime(System.currentTimeMillis());
        return blogMapper.insert(blogDO);
    }

    @Override
    public BlogDO getBlogById(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        BlogDO blogDO = BlogConvert.INSTANCE.convert(updateReqVO);
        blogDO.setUpdateTime(System.currentTimeMillis());
        blogMapper.updateById(blogDO);
    }
}
