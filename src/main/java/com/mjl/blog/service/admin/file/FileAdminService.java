package com.mjl.blog.service.admin.file;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.file.vo.*;
import com.mjl.blog.dal.dataobject.FileDO;

import java.util.List;

public interface FileAdminService {
    String upload(String name, String path, byte[] content,Integer module);
    FileDO getByPath(String path,Integer module);
    void delete(String path);
    byte[] getContent(String path);
    void updateStatus(UpdateStatusReqVO updateStatusReqVO);
    PageResult<FileDO> getList(TableReqVO tableReqVO);

    FileDO getById(Long id);

    void update(UpdateReqVO updateReqVO);

    void updateContent(String name, String path, byte[] content, Long id);

    void updateModule(UpdateModuleReqVO updateModuleReqVO);

    FileDO getFile(String path, Integer module);
}
