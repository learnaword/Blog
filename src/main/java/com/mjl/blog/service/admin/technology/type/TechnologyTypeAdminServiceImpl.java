package com.mjl.blog.service.admin.technology.type;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.type.vo.CreateReqVO;
import com.mjl.blog.controller.admin.technology.type.vo.TableReqVO;
import com.mjl.blog.controller.admin.technology.type.vo.UpdateReqVO;
import com.mjl.blog.controller.admin.technology.type.vo.UpdateStatusReqVO;
import com.mjl.blog.convert.admin.TechnologyTypeAdminConvert;
import com.mjl.blog.dal.dataobject.TechnologyTypeDO;
import com.mjl.blog.dal.mysql.TechnologyTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyTypeAdminServiceImpl implements TechnologyTypeAdminService {
    @Resource
    private TechnologyTypeMapper technologyTypeMapper;

    @Override
    public List<TechnologyTypeDO> getList() {
        return technologyTypeMapper.selectList(TechnologyTypeDO::getStatus, CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public TechnologyTypeDO getById(Long id) {
        return technologyTypeMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<TechnologyTypeDO> typeDOList =  technologyTypeMapper.selectList(new LambdaQueryWrapper<TechnologyTypeDO>().in(TechnologyTypeDO::getId,updateStatusReqVO.getIds()));
        typeDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        technologyTypeMapper.updateBatch(typeDOList);
    }

    @Override
    public PageResult<TechnologyTypeDO> getList(TableReqVO tableReqVO) {
        return technologyTypeMapper.selectPage(tableReqVO);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        TechnologyTypeDO typeDO = TechnologyTypeAdminConvert.INSTANCE.convert(updateReqVO);
        typeDO.setUpdateTime(System.currentTimeMillis());
        technologyTypeMapper.updateById(typeDO);
    }

    @Override
    public int create(CreateReqVO createReqVO) {
        TechnologyTypeDO typeDO = TechnologyTypeAdminConvert.INSTANCE.convert(createReqVO);
        typeDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        typeDO.setCreateTime(System.currentTimeMillis());
        typeDO.setUpdateTime(System.currentTimeMillis());
        return technologyTypeMapper.insert(typeDO);
    }

}
