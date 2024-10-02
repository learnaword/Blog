package com.mjl.blog.service.admin.technology.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;

import java.util.List;

public interface TechnologySoftAdminService {
    List<TechnologySoftDO> getSoftList();
    TechnologySoftDO getSoftById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<TechnologySoftDO> getList(TableReqVO tableReqVO);

    void update(UpdateReqVO updateReqVO);

    int create(CreateReqVO createReqVO);

    TechnologySoftDO getById(Long id);

    void delete(DeleteReqVO deleteReqVO);
}
