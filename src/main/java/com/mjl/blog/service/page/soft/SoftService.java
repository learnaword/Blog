package com.mjl.blog.service.page.soft;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.soft.vo.SoftInfoReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftReqVO;
import com.mjl.blog.dal.dataobject.SoftDO;

public interface SoftService {
    PageResult<SoftDO> getList(SoftReqVO softReqVO);

    SoftDO selectById(Long id);

    PageResult<SoftDO> getSoftInfos(SoftInfoReqVO softInfoReqVO, Long softId);
}
