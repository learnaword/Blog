package com.mjl.blog.service.admin.auto.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.config.vo.CreateReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.TableReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateStatusReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateReqVO;
import com.mjl.blog.convert.AutoConfigConvert;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.dal.mysql.AutoConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoConfigServiceImpl implements AutoConfigService {
    @Resource
    AutoConfigMapper autoConfigMapper;
    @Override
    public void update(UpdateReqVO updateReqVO) {
        AutoConfigDO autoConfigDO = AutoConfigConvert.INSTANCE.convert(updateReqVO);
        autoConfigDO.setUpdateTime(System.currentTimeMillis());
        autoConfigMapper.updateById(autoConfigDO);
    }

    @Override
    public PageResult<AutoConfigDO> getList(TableReqVO tableReqVO) {
        return autoConfigMapper.selectPage(tableReqVO);
    }
    @Override
    public int create(CreateReqVO createReqVO) {
        AutoConfigDO autoConfigDO = AutoConfigConvert.INSTANCE.convert(createReqVO);
        autoConfigDO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        autoConfigDO.setCreateTime(System.currentTimeMillis());
        autoConfigDO.setUpdateTime(System.currentTimeMillis());
        return autoConfigMapper.insert(autoConfigDO);
    }

    @Override
    public AutoConfigDO getById(Long id) {
        return autoConfigMapper.selectById(id);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateConfigsStatusReqVO) {
        List<AutoConfigDO> autoConfigDOList =  autoConfigMapper.selectList(new LambdaQueryWrapper<AutoConfigDO>().in(AutoConfigDO::getId,updateConfigsStatusReqVO.getIds()));
        autoConfigDOList.forEach(item -> item.setStatus(updateConfigsStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        autoConfigMapper.updateBatch(autoConfigDOList);
    }
}
