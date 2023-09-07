package com.mjl.blog.service.page.media;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.JsonUtils;
import com.mjl.blog.controller.page.media.vo.HotMediasRespVO;
import com.mjl.blog.controller.page.media.vo.MediaReqVO;
import com.mjl.blog.controller.page.media.vo.RelMediasRespVO;
import com.mjl.blog.controller.page.media.vo.TopMediasRespVO;
import com.mjl.blog.convert.page.MediaConvert;
import com.mjl.blog.dal.dataobject.MediaDO;
import com.mjl.blog.dal.mysql.MediaMapper;
import com.mjl.blog.dal.redis.RedisKeyConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MediaServiceImpl implements MediaService{
    @Resource
    MediaMapper mediaMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public PageResult<MediaDO> getNewList(MediaReqVO mediaReqVO) {
        return mediaMapper.selectPage(mediaReqVO);
    }

    @Override
    public MediaDO selectMediaById(Long id) {
        return mediaMapper.selectById(id);
    }

    @Override
    public List<RelMediasRespVO> getRelMedias(MediaDO media) {
        String redisKey = String.format(RedisKeyConstants.MEDIA_INFO_REL_LIST.getKeyTemplate(),media.getId());
        //如果redis里没有，就从数据库里获取。
        String medias = stringRedisTemplate.opsForValue().get(redisKey);
        List<RelMediasRespVO> relMedias;
        if (medias == null) {
            List<MediaDO> mediaDOList = mediaMapper.selectList(new QueryWrapper<MediaDO>()
                    .eq("status", CommonStatusEnum.ENABLE.getStatus())
                    .last("order by rand() limit 4"));
            relMedias = MediaConvert.INSTANCE.convertRel(mediaDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(relMedias), 100, TimeUnit.DAYS);
        } else {
            relMedias = JsonUtils.parseObject(medias, new TypeReference<List<RelMediasRespVO>>() {});
        }

        return relMedias;
    }

    @Override
    public List<HotMediasRespVO> getHotMedias(MediaDO media) {
        String redisKey = String.format(RedisKeyConstants.MEDIA_INFO_HOT_LIST.getKeyTemplate(),media.getId());
        //如果redis里没有，就从数据库里获取。
        String medias = stringRedisTemplate.opsForValue().get(redisKey);
        List<HotMediasRespVO> hotMedias;
        if (medias == null) {
            List<MediaDO> mediaDOList = mediaMapper.selectList(new QueryWrapper<MediaDO>()
                    .eq("status", CommonStatusEnum.ENABLE.getStatus())
                    .last("order by rand() limit 4"));
            hotMedias = MediaConvert.INSTANCE.convertHot(mediaDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(hotMedias), 100, TimeUnit.DAYS);
        } else {
            hotMedias = JsonUtils.parseObject(medias, new TypeReference<List<HotMediasRespVO>>() {});
        }
        return hotMedias;
    }

    @Override
    public List<TopMediasRespVO> getTopMedias() {
        String redisKey = RedisKeyConstants.MEDIA_INFO_TOP_LIST.getKeyTemplate();
        //如果redis里没有，就从数据库里获取。
        String medias = stringRedisTemplate.opsForValue().get(redisKey);
        List<TopMediasRespVO> topMedias;
        if (medias == null) {
            List<MediaDO> mediaDOList = mediaMapper.selectList(new QueryWrapper<MediaDO>()
                    .eq("is_top", 1)
                    .eq("status", CommonStatusEnum.ENABLE.getStatus())
                    .last("limit 4"));
            topMedias = MediaConvert.INSTANCE.convertRecommend(mediaDOList);
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(topMedias), 2, TimeUnit.HOURS);
        } else {
            topMedias = JsonUtils.parseObject(medias, new TypeReference<List<TopMediasRespVO>>() {});
        }

        return topMedias;
    }

    @Override
    public List<MediaDO> getNewMedias() {
        return mediaMapper.selectList(new QueryWrapper<MediaDO>()
                .eq("status", CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc("create_time").last("limit 10"));
    }

    @Override
    public MediaDO selectNextMedia(Long id) {
        return mediaMapper.selectOne(new LambdaQueryWrapper<MediaDO>()
                .gt(MediaDO::getId,id).orderByAsc(MediaDO::getId).last("limit 1"));
    }

    @Override
    public MediaDO selectPrevMedia(Long id) {
        return mediaMapper.selectOne(new LambdaQueryWrapper<MediaDO>()
                .lt(MediaDO::getId,id).orderByDesc(MediaDO::getId).last("limit 1"));
    }
}
