package com.mjl.blog.service.admin.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.*;
import com.mjl.blog.dal.dataobject.SoftDO;

import java.util.List;

public interface SoftAdminService {
    List<SoftDO> getSoftList();
    SoftDO getSoftById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<SoftDO> getList(TableReqVO tableReqVO);

    void update(UpdateReqVO updateReqVO);

    int create(CreateReqVO createReqVO);

    SoftDO getById(Long id);

    void delete(DeleteReqVO deleteReqVO);
}
