package com.mjl.blog.service.page.data;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.data.vo.ButtonInfoRespVo;
import com.mjl.blog.controller.page.data.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.ButtonInfoDO;

public interface ButtonInfoService {
    void insert(ButtonInfoRespVo buttonInfoRespVo);

    PageResult<ButtonInfoDO> getButtonInfoList(TableReqVO buttonInfoTableReqVO);
}
