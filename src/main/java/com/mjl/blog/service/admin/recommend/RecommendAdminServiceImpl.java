package com.mjl.blog.service.admin.recommend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.recommend.vo.*;
import com.mjl.blog.convert.admin.RecommendAdminConvert;
import com.mjl.blog.dal.dataobject.RecommendDO;
import com.mjl.blog.dal.mysql.RecommendMapper;
import com.mjl.blog.service.admin.recommend.RecommendAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendAdminServiceImpl implements RecommendAdminService {
    @Resource
    private RecommendMapper recommendMapper;

    @Override
    public List<RecommendDO> getRecommendList() {
        return recommendMapper.selectList(RecommendDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public RecommendDO getRecommendById(Long id) {
        return recommendMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<RecommendDO> recommendDOList =  recommendMapper.selectList(new LambdaQueryWrapper<RecommendDO>().in(RecommendDO::getId,updateStatusReqVO.getIds()));
        recommendDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        recommendMapper.updateBatch(recommendDOList);
    }

    @Override
    public PageResult<RecommendDO> getList(TableReqVO tableReqVO) {
        return recommendMapper.selectPage(tableReqVO);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        RecommendDO recommendDO = RecommendAdminConvert.INSTANCE.convert(updateReqVO);
        recommendDO.setUpdateTime(System.currentTimeMillis());
        recommendMapper.updateById(recommendDO);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        RecommendDO recommendDO = RecommendAdminConvert.INSTANCE.convert(createReqVO);
        recommendDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        recommendDO.setCreateTime(System.currentTimeMillis());
        recommendDO.setUpdateTime(System.currentTimeMillis());
        return recommendMapper.insert(recommendDO);
    }

    @Override
    public RecommendDO getById(Long id) {
        return recommendMapper.selectById(id);
    }

    @Override
    public void delete(DeleteReqVO deleteReqVO) {
        recommendMapper.deleteBatchIds(deleteReqVO.getIds());
    }

    @Override
    public List<RecommendDO> getRecommendList(List<Long> adTypes) {
        return recommendMapper.selectBatchIds(adTypes);
    }

    @Override
    public List<RecommendDO> getRecommendListSortByList(List<Integer> adTypes) {
        List<Long> longList = adTypes.stream().map(item -> item.longValue()).collect(Collectors.toList());;
        List<RecommendDO> recommendDOList = recommendMapper.selectBatchIds(longList);
        Collections.sort(recommendDOList, Comparator.comparingLong(item -> longList.indexOf(item.getId())));
        return recommendDOList;
    }

}
