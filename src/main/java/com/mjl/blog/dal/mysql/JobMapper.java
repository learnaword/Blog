package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.JobDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {
    default PageResult<JobDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<JobDO>()
                .eq(JobDO::getDeleted, reqVO.getDeleted())
                .orderByDesc(JobDO::getUpdateTime));
    }

    default JobDO selectByHandlerName(String handlerName) {
        return selectOne(JobDO::getHandlerName, handlerName);
    }}
