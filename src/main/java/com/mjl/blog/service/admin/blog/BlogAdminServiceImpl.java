package com.mjl.blog.service.admin.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.blog.vo.*;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.convert.admin.BlogAdminConvert;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.mysql.BlogMapper;
import com.mjl.blog.enums.BlogStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BlogAdminServiceImpl implements BlogAdminService {
    @Resource
    private BlogMapper blogMapper;
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
        BlogDO blogDO = BlogAdminConvert.INSTANCE.convert(createReqVO);
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
        BlogDO blogDO = BlogAdminConvert.INSTANCE.convert(updateReqVO);
        blogDO.setUpdateTime(System.currentTimeMillis());
        blogMapper.updateById(blogDO);
    }

    @Override
    public void replace(String searchStr, String replaceStr) {
        String sql = String.format("content = REPLACE(content, '%s', '%s')", searchStr, replaceStr);
        LambdaUpdateWrapper<BlogDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql(sql);
        blogMapper.update(null,updateWrapper);
    }

    @Override
    public boolean fileIsUse(String name) {
        List<BlogDO> blogDOList = blogMapper.selectList(new LambdaQueryWrapper<BlogDO>().like(BlogDO::getContent,name));
        if(blogDOList.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public DataRespVO getEcharsBlogData(Long start, Long end) {
        DataRespVO LogDataRespVO = new DataRespVO();
        int diffNum = (int) TimeUnit.MILLISECONDS.toDays(end - start) + 1;
        LocalDate startTime = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        Long[] counts = new Long[diffNum];
        String[] days =  new String[diffNum];
        Long total = 0L;

        for (int i = 0; i < diffNum; i++) {
            Long preDate = startTime.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long nowDate = startTime.plusDays(i+1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();;
            Long sum = blogMapper.selectCount(new LambdaQueryWrapper<BlogDO>()
                    .between(BlogDO::getCreateTime,preDate,nowDate)
                    .eq(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()));
            counts[i] = sum;
            days[i] = DateUtils.timestampToString(preDate);
            total = total + sum;
        }

        LogDataRespVO.setCounts(counts).setDays(days).setTotal(total);
        return LogDataRespVO;
    }
}
