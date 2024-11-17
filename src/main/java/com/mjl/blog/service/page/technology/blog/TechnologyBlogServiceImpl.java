package com.mjl.blog.service.page.technology.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.JsonUtils;
import com.mjl.blog.controller.page.technology.blog.vo.*;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.convert.page.TechnologyBlogConvert;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;
import com.mjl.blog.dal.mysql.TechnologyBlogMapper;
import com.mjl.blog.dal.redis.RedisKeyConstants;
import com.mjl.blog.enums.BlogStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TechnologyBlogServiceImpl implements TechnologyBlogService {

    @Resource
    private TechnologyBlogMapper technologyBlogMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<TechnologyBlogDO> getOrderList() {
        return technologyBlogMapper.selectList(new QueryWrapper<TechnologyBlogDO>()
                .eq("is_top", 1)
                .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                .orderByAsc("create_time").last("limit 6"));
    }

    @Override
    public PageResult<TechnologyBlogDO> getNewList(IndexReqVO indexReqVO) {
        return technologyBlogMapper.selectPage(indexReqVO);
    }

    @Override
    public PageResult<TechnologyBlogDO> getSoftDetails(SoftDetailsReqVO softDetailsReqVO,Long softId) {
        return technologyBlogMapper.selectPage(softDetailsReqVO,softId);
    }

    @Override
    public PageResult<TechnologyBlogDO> softDetailTypeBlogs(SoftDetailTypeReqVO softDetailTypeReqVO, Long softId, Integer softSection) {
        return technologyBlogMapper.selectPage(softDetailTypeReqVO,softId,softSection);

    }

    @Override
    public TechnologyBlogDO selectBlogById(Long id) {
        return technologyBlogMapper.selectById(id);
    }

    @Override
    public List<RelBlogsRespVO> getRelBlogs(TechnologyBlogDO blog) {

        String redisKey = String.format(RedisKeyConstants.TECHNOLOGY_INFO_REL_LIST.getKeyTemplate(),blog.getId());
        //如果redis里没有，就从数据库里获取。
        String blogs = stringRedisTemplate.opsForValue().get(redisKey);
        List<RelBlogsRespVO> relBlogs;
        if (blogs == null) {
           List<TechnologyBlogDO> blogDOList = technologyBlogMapper.selectList(new QueryWrapper<TechnologyBlogDO>()
                    .eq("soft_id", blog.getSoftId())
                    .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                    .last("order by rand() limit 4"));
            relBlogs = TechnologyBlogConvert.INSTANCE.convertRel(blogDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(relBlogs), 100, TimeUnit.DAYS);
        } else {
            relBlogs = JsonUtils.parseObject(blogs, new TypeReference<List<RelBlogsRespVO>>() {});
        }

        return relBlogs;
    }

    @Override
    public List<RelBlogsRespVO> getHotBlogs(TechnologyBlogDO blog) {
        String redisKey = String.format(RedisKeyConstants.TECHNOLOGY_INFO_HOT_LIST.getKeyTemplate(),blog.getId());
        //如果redis里没有，就从数据库里获取。
        String blogs = stringRedisTemplate.opsForValue().get(redisKey);
        List<RelBlogsRespVO> hotBlogs;
        if (blogs == null) {
            List<TechnologyBlogDO> blogDOList = technologyBlogMapper.selectList(new QueryWrapper<TechnologyBlogDO>()
                    .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                    .last("order by rand() limit 4"));
            hotBlogs = TechnologyBlogConvert.INSTANCE.convertHot(blogDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(hotBlogs), 100, TimeUnit.DAYS);
        } else {
            hotBlogs = JsonUtils.parseObject(blogs, new TypeReference<List<RelBlogsRespVO>>() {});
        }
        return hotBlogs;
    }

    @Override
    public List<TopBlogsRespVO> getTopBlogs(TechnologyBlogDO blog) {

        String redisKey = String.format(RedisKeyConstants.TECHNOLOGY_INFO_TOP_LIST.getKeyTemplate(),blog.getSoftId());
        //如果redis里没有，就从数据库里获取。
        String blogs = stringRedisTemplate.opsForValue().get(redisKey);
        List<TopBlogsRespVO> topBlogs;
        if (blogs == null) {
            List<TechnologyBlogDO> blogDOList = technologyBlogMapper.selectList(new QueryWrapper<TechnologyBlogDO>()
                    .eq("soft_id", blog.getSoftId())
                    .eq("is_top", 1)
                    .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                    .orderByDesc("rank_score")
                    .last("limit 3"));
            topBlogs = TechnologyBlogConvert.INSTANCE.convertRecommend(blogDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(topBlogs), 2, TimeUnit.HOURS);
        } else {
            topBlogs = JsonUtils.parseObject(blogs, new TypeReference<List<TopBlogsRespVO>>() {});
        }

        return topBlogs;
    }

    @Override
    public List<TechnologyBlogDO> getNewBlogs() {
        return technologyBlogMapper.selectList(new QueryWrapper<TechnologyBlogDO>()
                .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                .orderByDesc("create_time").last("limit 10"));
    }

    @Override
    public TechnologyBlogDO selectNextBlog(Long id, Long softId) {
        return technologyBlogMapper.selectOne(new LambdaQueryWrapper<TechnologyBlogDO>().eq(TechnologyBlogDO::getSoftId,softId)
                .gt(TechnologyBlogDO::getId,id).eq(TechnologyBlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()).orderByAsc(TechnologyBlogDO::getId).last("limit 1"));
    }

    @Override
    public TechnologyBlogDO selectPrevBlog(Long id, Long softId) {
        return technologyBlogMapper.selectOne(new LambdaQueryWrapper<TechnologyBlogDO>().eq(TechnologyBlogDO::getSoftId,softId)
                .lt(TechnologyBlogDO::getId,id).eq(TechnologyBlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()).orderByDesc(TechnologyBlogDO::getId).last("limit 1"));
    }

    @Override
    public List<TechnologyBlogDO> find(String keyboard) {
        return technologyBlogMapper.selectList(new LambdaQueryWrapper<TechnologyBlogDO>().like(TechnologyBlogDO::getTitle,keyboard)
                .eq(TechnologyBlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()).last("limit 10"));
    }

}
