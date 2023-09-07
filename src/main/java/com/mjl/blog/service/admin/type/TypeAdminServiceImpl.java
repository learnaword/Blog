package com.mjl.blog.service.admin.type;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.type.vo.*;
import com.mjl.blog.convert.admin.TypeAdminConvert;
import com.mjl.blog.dal.dataobject.TypeDO;
import com.mjl.blog.dal.mysql.TypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAdminServiceImpl implements TypeAdminService {
    @Resource
    private TypeMapper typeMapper;

    @Override
    public List<TypeDO> getList() {
        return typeMapper.selectList(TypeDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public TypeDO getById(Long id) {
        return typeMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<TypeDO> typeDOList =  typeMapper.selectList(new LambdaQueryWrapper<TypeDO>().in(TypeDO::getId,updateStatusReqVO.getIds()));
        typeDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        typeMapper.updateBatch(typeDOList);
    }

    @Override
    public PageResult<TypeDO> getList(TableReqVO tableReqVO) {
        return typeMapper.selectPage(tableReqVO);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        TypeDO typeDO = TypeAdminConvert.INSTANCE.convert(updateReqVO);
        typeDO.setUpdateTime(System.currentTimeMillis());
        typeMapper.updateById(typeDO);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        TypeDO typeDO = TypeAdminConvert.INSTANCE.convert(createReqVO);
        typeDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        typeDO.setCreateTime(System.currentTimeMillis());
        typeDO.setUpdateTime(System.currentTimeMillis());
        return typeMapper.insert(typeDO);
    }

}
