package com.mjl.blog.service.page.technology.type;

import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.dal.dataobject.TypeDO;
import com.mjl.blog.dal.mysql.TypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyTypeServiceImpl implements TechnologyTypeService {

    @Resource
    private TypeMapper typeMapper;
    @Override
    public List<TypeDO> getList() {
        return typeMapper.selectList("status", CommonStatusEnum.ENABLE.getStatus());
    }
}