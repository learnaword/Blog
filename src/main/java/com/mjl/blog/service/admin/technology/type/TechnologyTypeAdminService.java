package com.mjl.blog.service.admin.technology.type;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.type.vo.CreateReqVO;
import com.mjl.blog.controller.admin.technology.type.vo.TableReqVO;
import com.mjl.blog.controller.admin.technology.type.vo.UpdateReqVO;
import com.mjl.blog.controller.admin.technology.type.vo.UpdateStatusReqVO;
import com.mjl.blog.dal.dataobject.TechnologyTypeDO;

import java.util.List;

public interface TechnologyTypeAdminService {
    List<TechnologyTypeDO> getList();
    TechnologyTypeDO getById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<TechnologyTypeDO> getList(TableReqVO tableReqVO);

    void update(UpdateReqVO updateReqVO);

    int create(CreateReqVO createReqVO);

}
