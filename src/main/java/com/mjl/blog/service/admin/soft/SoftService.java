package com.mjl.blog.service.admin.soft;

import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.dal.dataobject.SoftDO;

import java.util.List;

public interface SoftService {
    List<SoftDO> getSoftList();
    SoftDO getSoftById(Long id);
}
