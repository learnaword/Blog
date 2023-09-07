package com.mjl.blog.service.admin.media;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.media.vo.*;
import com.mjl.blog.dal.dataobject.MediaDO;

import java.util.List;

public interface MediaAdminService {
    DateCountsRespVO getDateCounts(int preNum);

    PageResult<MediaDO> getList(TableReqVO blogTableReqVO);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    void updateTops(UpdateTopsReqVO updateTopsReqVO);

    int create(CreateReqVO createReqVO);

    MediaDO getById(Long id);

    void update(UpdateReqVO updateReqVO);
}
