package com.mjl.blog.service.admin.type;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.type.vo.*;
import com.mjl.blog.dal.dataobject.TypeDO;

import java.util.List;

public interface TypeAdminService {
    List<TypeDO> getList();
    TypeDO getById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<TypeDO> getList(TableReqVO tableReqVO);

    void update(UpdateReqVO updateReqVO);

    int create(CreateReqVO createReqVO);

}
