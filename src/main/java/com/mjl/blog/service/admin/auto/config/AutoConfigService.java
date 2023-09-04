package com.mjl.blog.service.admin.auto.config;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.config.vo.CreateReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.TableReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateStatusReqVO;
import com.mjl.blog.controller.admin.auto.config.vo.UpdateReqVO;
import com.mjl.blog.dal.dataobject.AutoConfigDO;

public interface AutoConfigService {

    PageResult<AutoConfigDO> getList(TableReqVO blogTableReqVO);

    int create(CreateReqVO createReqVO);

    AutoConfigDO getById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    void update(UpdateReqVO updateReqVO);
}
