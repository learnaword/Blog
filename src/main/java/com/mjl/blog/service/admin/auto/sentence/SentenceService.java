package com.mjl.blog.service.admin.auto.sentence;

import com.mjl.blog.common.pojo.FileExecutionInfo;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.TableReqVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateReqVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateStatusReqVO;
import com.mjl.blog.dal.dataobject.SentenceDO;

import java.util.List;

public interface SentenceService {

    PageResult<SentenceDO> getList(TableReqVO tableReqVO);

    void updateStatus(UpdateStatusReqVO updateStatusReqVO);

    SentenceDO getById(Long id);

    void update(UpdateReqVO updateReqVO);

    void fileCreate(byte[] content, String path, String name);

    List<SentenceDO> getListLimit(int i);

    void updateList(List<SentenceDO> sentenceDOList);
}
