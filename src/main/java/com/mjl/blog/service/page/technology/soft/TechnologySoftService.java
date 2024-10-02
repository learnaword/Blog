package com.mjl.blog.service.page.technology.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;

public interface TechnologySoftService {
    PageResult<TechnologySoftDO> getList(SoftReqVO softReqVO);

    TechnologySoftDO selectById(Long id);

    PageResult<TechnologySoftDO> getSoftInfos(SoftInfoReqVO softInfoReqVO, Long softId);
}
