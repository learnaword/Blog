package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.job.vo.*;
import com.mjl.blog.dal.dataobject.JobDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JobAdminConvert {
    JobAdminConvert INSTANCE = Mappers.getMapper(JobAdminConvert.class);


    PageResult<TableRespVO> convert(PageResult<JobDO> list);

    JobDO convert(CreateReqVO createReqVO);

    JobDO convert(UpdateReqVO updateReqVO);

    GetRespVO convert2(JobDO byId);

    TableRespVO convert3(JobDO item);

    List<JobListVO> convert(List<JobDO> jobList);
}
