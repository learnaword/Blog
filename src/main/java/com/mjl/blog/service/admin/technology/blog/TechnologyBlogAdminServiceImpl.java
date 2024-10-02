package com.mjl.blog.service.admin.technology.blog;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.DateUtils;
import com.mjl.blog.controller.admin.technology.blog.vo.*;
import com.mjl.blog.controller.admin.echars.vo.DataRespVO;
import com.mjl.blog.convert.admin.TechnologyBlogAdminConvert;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;
import com.mjl.blog.dal.mysql.TechnologyBlogMapper;
import com.mjl.blog.enums.BlogStatusEnum;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TechnologyBlogAdminServiceImpl implements TechnologyBlogAdminService {
    @Resource
    private TechnologyBlogMapper technologyBlogMapperBlogMapper;
    @Override
    public BlogTypeCountsRespVO getArticleTypeCounts() {
        BlogTypeCountsRespVO blogTypeCountsRespVO = new BlogTypeCountsRespVO()
                .setDraftCounts(technologyBlogMapperBlogMapper.selectCount(TechnologyBlogDO::getStatus, BlogStatusEnum.DRAFT.getStatus()))
                .setResercedCounts(technologyBlogMapperBlogMapper.selectCount(TechnologyBlogDO::getStatus,BlogStatusEnum.RESERVED.getStatus()))
                .setPublishedCounts(technologyBlogMapperBlogMapper.selectCount(TechnologyBlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()))
                .setSpmCounts(technologyBlogMapperBlogMapper.selectCount(TechnologyBlogDO::getStatus,BlogStatusEnum.SPAM.getStatus()));
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
            Long sum = technologyBlogMapperBlogMapper.selectCount(new LambdaQueryWrapper<TechnologyBlogDO>()
                    .between(TechnologyBlogDO::getCreateTime,preDate,nowDate)
                    .eq(TechnologyBlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()));
            BlogDateCounts.add(sum);
        }
        blogDateCountsRespVO.setBlogDateCounts(BlogDateCounts);
        return blogDateCountsRespVO;
    }

    @Override
    public PageResult<TechnologyBlogDO> getBlogList(BlogTableReqVO blogTableReqVO) {
        return technologyBlogMapperBlogMapper.selectPage(blogTableReqVO);
    }

    @Override
    public void updateBlogsStatus(UpdateBlogsStatusReqVO updateBlogsStatusReqVO) {
        List<TechnologyBlogDO> blogDOList =  technologyBlogMapperBlogMapper.selectList(new LambdaQueryWrapper<TechnologyBlogDO>().in(TechnologyBlogDO::getId,updateBlogsStatusReqVO.getIds()));
        blogDOList.forEach(item -> item.setStatus(updateBlogsStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        technologyBlogMapperBlogMapper.updateBatch(blogDOList);
    }

    @Override
    public void updateBlogsTop(UpdateBlogsTopReqVO updateBlogsTopReqVO) {
        List<TechnologyBlogDO> blogDOList =  technologyBlogMapperBlogMapper.selectList(new LambdaQueryWrapper<TechnologyBlogDO>().in(TechnologyBlogDO::getId,updateBlogsTopReqVO.getIds()));
        blogDOList.forEach(item -> item.setIsTop(updateBlogsTopReqVO.getIsTop()).setUpdateTime(System.currentTimeMillis()));
        technologyBlogMapperBlogMapper.updateBatch(blogDOList);
    }

    @Override
    public void updateBlogsRecommend(UpdateBlogsRecommendReqVO updateBlogsRecommendReqVO) {
        List<TechnologyBlogDO> blogDOList =  technologyBlogMapperBlogMapper.selectList(new LambdaQueryWrapper<TechnologyBlogDO>().in(TechnologyBlogDO::getId,updateBlogsRecommendReqVO.getIds()));
        blogDOList.forEach(item -> item.setIsRecommend(updateBlogsRecommendReqVO.getIsRecommend()).setUpdateTime(System.currentTimeMillis()));
        technologyBlogMapperBlogMapper.updateBatch(blogDOList);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        TechnologyBlogDO blogDO = TechnologyBlogAdminConvert.INSTANCE.convert(createReqVO);
        blogDO.setStatus(BlogStatusEnum.PUBLISHED.getStatus());
        blogDO.setCreateTime(System.currentTimeMillis());
        blogDO.setUpdateTime(System.currentTimeMillis());
        return technologyBlogMapperBlogMapper.insert(blogDO);
    }

    @Override
    public TechnologyBlogDO getBlogById(Long id) {
        return technologyBlogMapperBlogMapper.selectById(id);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        TechnologyBlogDO blogDO = TechnologyBlogAdminConvert.INSTANCE.convert(updateReqVO);
        blogDO.setUpdateTime(System.currentTimeMillis());
        technologyBlogMapperBlogMapper.updateById(blogDO);
    }

    @Override
    public void replace(String searchStr, String replaceStr) {
        String sql = String.format("content = REPLACE(content, '%s', '%s')", searchStr, replaceStr);
        LambdaUpdateWrapper<TechnologyBlogDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql(sql);
        technologyBlogMapperBlogMapper.update(null,updateWrapper);
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
            Long sum = technologyBlogMapperBlogMapper.selectCount(new LambdaQueryWrapper<TechnologyBlogDO>()
                    .between(TechnologyBlogDO::getCreateTime,preDate,nowDate)
                    .eq(TechnologyBlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()));
            counts[i] = sum;
            days[i] = DateUtils.timestampToString(preDate);
            total = total + sum;
        }

        LogDataRespVO.setCounts(counts).setDays(days).setTotal(total);
        return LogDataRespVO;
    }

    public void autoUpdateBlog() {
        // 统计满足条件的记录数
        Long blogCounts = technologyBlogMapperBlogMapper.selectCount(TechnologyBlogDO::getStatus,-1);
        //更新文章时间
        Wrapper<TechnologyBlogDO> wrapper = Wrappers.<TechnologyBlogDO>update().set("create_time", System.currentTimeMillis())
                .set("update_time",System.currentTimeMillis()).eq("status", -1);
        technologyBlogMapperBlogMapper.update(null, wrapper);
        // 更新文章的数,

        LambdaQueryWrapper<TechnologyBlogDO> lambdaQueryWrapper = new LambdaQueryWrapper<TechnologyBlogDO>();
        lambdaQueryWrapper.eq(TechnologyBlogDO::getStatus,-1);

        int updateBlogNum= 0;
        if(DateUtils.isMidnight()){
            updateBlogNum= Math.max((int) Math.floor(blogCounts / 70), 0);
            lambdaQueryWrapper.orderByAsc(TechnologyBlogDO::getId);
        }else{
            updateBlogNum = Math.max((int) Math.floor(blogCounts / 70), 1);
            lambdaQueryWrapper.orderByDesc(TechnologyBlogDO::getId);
        }

        lambdaQueryWrapper.last("limit "+updateBlogNum);

        List<TechnologyBlogDO> blogDOList = technologyBlogMapperBlogMapper.selectList(lambdaQueryWrapper);
        blogDOList.stream().forEach(item->item.setStatus(1));
        technologyBlogMapperBlogMapper.updateBatch(blogDOList);
    }
}
