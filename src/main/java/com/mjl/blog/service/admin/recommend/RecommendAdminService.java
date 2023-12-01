package com.mjl.blog.service.admin.recommend;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.recommend.vo.*;
import com.mjl.blog.dal.dataobject.RecommendDO;

import java.util.List;

public interface RecommendAdminService {
    List<RecommendDO> getRecommendList();
    RecommendDO getRecommendById(Long id);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    PageResult<RecommendDO> getList(TableReqVO tableReqVO);

    void update(UpdateReqVO updateReqVO);

    int create(CreateReqVO createReqVO);

    RecommendDO getById(Long id);

    void delete(DeleteReqVO deleteReqVO);

    List<RecommendDO> getRecommendList(List<Long> adTypes);

    List<RecommendDO> getRecommendListSortByList(List<Integer> adTypes);
    }
