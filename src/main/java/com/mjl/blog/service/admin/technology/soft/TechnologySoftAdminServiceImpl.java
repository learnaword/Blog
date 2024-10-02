package com.mjl.blog.service.admin.technology.soft;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.soft.vo.*;
import com.mjl.blog.convert.admin.TechnologySoftAdminConvert;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import com.mjl.blog.dal.mysql.TechnologySoftMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologySoftAdminServiceImpl implements TechnologySoftAdminService {
    @Resource
    private TechnologySoftMapper technologySoftMapper;

    @Override
    public List<TechnologySoftDO> getSoftList() {
        return technologySoftMapper.selectList(TechnologySoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public TechnologySoftDO getSoftById(Long id) {
        return technologySoftMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<TechnologySoftDO> softDOList =  technologySoftMapper.selectList(new LambdaQueryWrapper<TechnologySoftDO>().in(TechnologySoftDO::getId,updateStatusReqVO.getIds()));
        softDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        technologySoftMapper.updateBatch(softDOList);
    }

    @Override
    public PageResult<TechnologySoftDO> getList(TableReqVO tableReqVO) {
        return technologySoftMapper.selectPage(tableReqVO);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        TechnologySoftDO softDO = TechnologySoftAdminConvert.INSTANCE.convert(updateReqVO);
        softDO.setUpdateTime(System.currentTimeMillis());
        technologySoftMapper.updateById(softDO);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        TechnologySoftDO softDO = TechnologySoftAdminConvert.INSTANCE.convert(createReqVO);
        softDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        softDO.setCreateTime(System.currentTimeMillis());
        softDO.setUpdateTime(System.currentTimeMillis());
        return technologySoftMapper.insert(softDO);
    }

    @Override
    public TechnologySoftDO getById(Long id) {
        return technologySoftMapper.selectById(id);
    }

    @Override
    public void delete(DeleteReqVO deleteReqVO) {
        technologySoftMapper.deleteBatchIds(deleteReqVO.getIds());
    }
}
