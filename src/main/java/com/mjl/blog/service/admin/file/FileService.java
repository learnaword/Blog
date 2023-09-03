package com.mjl.blog.service.admin.file;

import com.mjl.blog.dal.dataobject.FileDO;

public interface FileService {
    String upload(String name, String path, byte[] content);

    FileDO getByPath(String path);

    void delete(String path);

    byte[] getContent(String path);
}
