package com.mjl.blog.service.page.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.JsonUtils;
import com.mjl.blog.controller.page.blog.vo.IndexReqVO;
import com.mjl.blog.controller.page.blog.vo.TopBlogsRespVO;
import com.mjl.blog.controller.page.blog.vo.RelBlogsRespVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailTypeReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailsReqVO;
import com.mjl.blog.convert.page.BlogConvert;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.mysql.BlogMapper;
import com.mjl.blog.dal.redis.RedisKeyConstants;
import com.mjl.blog.enums.BlogStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BlogServiceImpl implements BlogService{

    @Resource
    private BlogMapper blogMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<BlogDO> getOrderList() {
        return blogMapper.selectList(new QueryWrapper<BlogDO>().eq("soft_id",83)
                .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                .orderByAsc("create_time").last("limit 10"));
    }

    @Override
    public PageResult<BlogDO> getNewList(IndexReqVO indexReqVO) {
        return blogMapper.selectPage(indexReqVO);
    }

    @Override
    public PageResult<BlogDO> getSoftDetails(SoftDetailsReqVO softDetailsReqVO,Long softId) {
        return blogMapper.selectPage(softDetailsReqVO,softId);
    }

    @Override
    public PageResult<BlogDO> softDetailTypeBlogs(SoftDetailTypeReqVO softDetailTypeReqVO, Long softId, Integer softSection) {
        return blogMapper.selectPage(softDetailTypeReqVO,softId,softSection);

    }

    @Override
    public BlogDO selectBlogById(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public List<RelBlogsRespVO> getRelBlogs(BlogDO blog) {

        String redisKey = String.format(RedisKeyConstants.BLOG_INFO_REL_LIST.getKeyTemplate(),blog.getId());
        //如果redis里没有，就从数据库里获取。
        String blogs = stringRedisTemplate.opsForValue().get(redisKey);
        List<RelBlogsRespVO> relBlogs;
        if (blogs == null) {
           List<BlogDO> blogDOList = blogMapper.selectList(new QueryWrapper<BlogDO>()
                    .eq("soft_id", blog.getSoftId())
                    .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                    .last("order by rand() limit 4"));
            relBlogs = BlogConvert.INSTANCE.convertRel(blogDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(relBlogs), 100, TimeUnit.DAYS);
        } else {
            relBlogs = JsonUtils.parseObject(blogs, new TypeReference<List<RelBlogsRespVO>>() {});
        }

        return relBlogs;
    }

    @Override
    public List<RelBlogsRespVO> getHotBlogs(BlogDO blog) {
        String redisKey = String.format(RedisKeyConstants.BLOG_INFO_HOT_LIST.getKeyTemplate(),blog.getId());
        //如果redis里没有，就从数据库里获取。
        String blogs = stringRedisTemplate.opsForValue().get(redisKey);
        List<RelBlogsRespVO> hotBlogs;
        if (blogs == null) {
            List<BlogDO> blogDOList = blogMapper.selectList(new QueryWrapper<BlogDO>()
                    .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                    .last("order by rand() limit 4"));
            hotBlogs = BlogConvert.INSTANCE.convertHot(blogDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(hotBlogs), 100, TimeUnit.DAYS);
        } else {
            hotBlogs = JsonUtils.parseObject(blogs, new TypeReference<List<RelBlogsRespVO>>() {});
        }
        return hotBlogs;
    }

    @Override
    public List<TopBlogsRespVO> getTopBlogs(BlogDO blog) {

        String redisKey = String.format(RedisKeyConstants.BLOG_INFO_TOP_LIST.getKeyTemplate(),blog.getSoftId());
        //如果redis里没有，就从数据库里获取。
        String blogs = stringRedisTemplate.opsForValue().get(redisKey);
        List<TopBlogsRespVO> topBlogs;
        if (blogs == null) {
            List<BlogDO> blogDOList = blogMapper.selectList(new QueryWrapper<BlogDO>()
                    .eq("soft_id", blog.getSoftId())
                    .eq("is_top", 1)
                    .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                    .orderByDesc("rank_score")
                    .last("limit 3"));
            topBlogs = BlogConvert.INSTANCE.convertRecommend(blogDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(topBlogs), 2, TimeUnit.HOURS);
        } else {
            topBlogs = JsonUtils.parseObject(blogs, new TypeReference<List<TopBlogsRespVO>>() {});
        }

        return topBlogs;
    }

    @Override
    public List<BlogDO> getNewBlogs(BlogDO blog) {
        return blogMapper.selectList(new QueryWrapper<BlogDO>()
                .eq("status", BlogStatusEnum.PUBLISHED.getStatus())
                .orderByDesc("create_time").last("limit 10"));
    }

    @Override
    public BlogDO selectNextBlog(Long id, Long softId) {
        return blogMapper.selectOne(new LambdaQueryWrapper<BlogDO>().eq(BlogDO::getSoftId,softId)
                .gt(BlogDO::getId,id).eq(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()).orderByAsc(BlogDO::getId).last("limit 1"));
    }

    @Override
    public BlogDO selectPrevBlog(Long id, Long softId) {
        return blogMapper.selectOne(new LambdaQueryWrapper<BlogDO>().eq(BlogDO::getSoftId,softId)
                .lt(BlogDO::getId,id).eq(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()).orderByDesc(BlogDO::getId).last("limit 1"));
    }

    @Override
    public List<BlogDO> find(String keyboard) {
        return blogMapper.selectList(new LambdaQueryWrapper<BlogDO>().like(BlogDO::getTitle,keyboard)
                .eq(BlogDO::getStatus,BlogStatusEnum.PUBLISHED.getStatus()).last("limit 10"));
    }

}
