package com.mjl.blog.service.page.type;

import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.dal.dataobject.TypeDO;
import com.mjl.blog.dal.mysql.TypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Resource
    private TypeMapper typeMapper;
    @Override
    public List<TypeDO> getList() {
        return typeMapper.selectList("status", CommonStatusEnum.ENABLE.getStatus());
    }
}
