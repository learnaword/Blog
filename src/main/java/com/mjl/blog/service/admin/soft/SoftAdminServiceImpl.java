package com.mjl.blog.service.admin.soft;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.*;
import com.mjl.blog.convert.admin.SoftAdminConvert;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.mysql.SoftMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftAdminServiceImpl implements SoftAdminService {
    @Resource
    private SoftMapper softMapper;

    @Override
    public List<SoftDO> getSoftList() {
        return softMapper.selectList(SoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public SoftDO getSoftById(Long id) {
        return softMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<SoftDO> softDOList =  softMapper.selectList(new LambdaQueryWrapper<SoftDO>().in(SoftDO::getId,updateStatusReqVO.getIds()));
        softDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        softMapper.updateBatch(softDOList);
    }

    @Override
    public PageResult<SoftDO> getList(TableReqVO tableReqVO) {
        return softMapper.selectPage(tableReqVO);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        SoftDO softDO = SoftAdminConvert.INSTANCE.convert(updateReqVO);
        softDO.setUpdateTime(System.currentTimeMillis());
        softMapper.updateById(softDO);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        SoftDO softDO = SoftAdminConvert.INSTANCE.convert(createReqVO);
        softDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        softDO.setCreateTime(System.currentTimeMillis());
        softDO.setUpdateTime(System.currentTimeMillis());
        return softMapper.insert(softDO);
    }

    @Override
    public SoftDO getById(Long id) {
        return softMapper.selectById(id);
    }

    @Override
    public void delete(DeleteReqVO deleteReqVO) {
        softMapper.deleteBatchIds(deleteReqVO.getIds());
    }
}
