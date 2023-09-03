package com.mjl.blog.service.admin.soft;

import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.mysql.SoftMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftServiceImpl implements SoftService{
    @Resource
    SoftMapper softMapper;

    @Override
    public List<SoftDO> getSoftList() {
        return softMapper.selectList(SoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }
}
