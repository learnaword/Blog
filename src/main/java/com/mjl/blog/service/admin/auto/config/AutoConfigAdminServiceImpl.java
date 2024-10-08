package com.mjl.blog.service.admin.auto.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.config.vo.CreateReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.TableReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateStatusReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateReqVO;
import com.mjl.blog.convert.admin.AutoConfigAdminConvert;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.dal.mysql.AutoConfigAdminMapper;
import com.mjl.blog.enums.BlogStatusEnum;
import com.mjl.blog.service.admin.blog.BlogAdminService;
import com.mjl.blog.service.admin.soft.SoftAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoConfigAdminServiceImpl implements AutoConfigAdminService {
    @Resource
    private AutoConfigAdminMapper autoConfigMapper;
    @Resource
    private SoftAdminService softService;

    @Resource
    private BlogAdminService blogAdminService;
    @Override
    public void update(UpdateReqVO updateReqVO) {
        AutoConfigDO oldAutoConfig = autoConfigMapper.selectById(updateReqVO.getId());
        AutoConfigDO autoConfigDO = AutoConfigAdminConvert.INSTANCE.convert(updateReqVO);
        autoConfigDO.setUpdateTime(System.currentTimeMillis());
        autoConfigMapper.updateById(autoConfigDO);

        //更新博客推荐
        blogAdminService.updateBlogs(autoConfigDO.getSoftId(),oldAutoConfig.getAdTypes(),autoConfigDO.getAdTypes());
    }

    @Override
    public List<AutoConfigDO> getList() {
        return autoConfigMapper.selectList(AutoConfigDO::getStatus,CommonStatusEnum.ENABLE.getStatus());
    }

    @Override
    public AutoConfigDO getByBlogTitle(String title) {
        List<AutoConfigDO> autoConfigDOList = getList();
        AutoConfigDO result=null;
        SoftDO mainSoftDO = null;
        for (AutoConfigDO autoConfigDO:autoConfigDOList) {
            SoftDO softDO = softService.getSoftById(autoConfigDO.getSoftId());
            if(title.contains(softDO.getTitle()) && autoConfigDO.getBlogStatus().equals(BlogStatusEnum.PUBLISHED.getStatus())){
                if(result==null){
                    result = autoConfigDO;
                    mainSoftDO = softDO;
                }else{
                    if(softDO.getTitle().length() > mainSoftDO.getTitle().length() ){
                        result = autoConfigDO;
                        mainSoftDO = softDO;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public PageResult<AutoConfigDO> getList(TableReqVO tableReqVO) {
        return autoConfigMapper.selectPage(tableReqVO);
    }
    @Override
    public int create(CreateReqVO createReqVO) {
        AutoConfigDO autoConfigDO = AutoConfigAdminConvert.INSTANCE.convert(createReqVO);
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
