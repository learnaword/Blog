package com.mjl.blog.service.page.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.soft.vo.SoftInfoReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftReqVO;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.mysql.SoftMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SoftServiceImpl implements SoftService{

    @Resource
    SoftMapper softMapper;
    @Override
    public PageResult<SoftDO> getList(SoftReqVO softReqVO) {
        return softMapper.selectPage(softReqVO);
    }

    @Override
    public SoftDO selectById(Long id) {
        return softMapper.selectById(id);
    }

    @Override
    public PageResult<SoftDO> getSoftInfos(SoftInfoReqVO softInfoReqVO, Long typeId) {
        return softMapper.selectPage(softInfoReqVO,typeId);
    }
}
