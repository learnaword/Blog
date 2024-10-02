package com.mjl.blog.service.page.technology.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import com.mjl.blog.dal.mysql.SoftMapper;
import com.mjl.blog.dal.mysql.TechnologySoftMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TechnologySoftServiceImpl implements TechnologySoftService {

    @Resource
    TechnologySoftMapper technologySoftMapper;
    @Override
    public PageResult<TechnologySoftDO> getList(SoftReqVO softReqVO) {
        return technologySoftMapper.selectPage(softReqVO);
    }

    @Override
    public TechnologySoftDO selectById(Long id) {
        return technologySoftMapper.selectById(id);
    }

    @Override
    public PageResult<TechnologySoftDO> getSoftInfos(SoftInfoReqVO softInfoReqVO, Long typeId) {
        return technologySoftMapper.selectPage(softInfoReqVO,typeId);
    }
}
