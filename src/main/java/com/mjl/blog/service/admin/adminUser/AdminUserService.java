package com.mjl.blog.service.admin.adminUser;

import com.mjl.blog.dal.dataobject.AdminUserDO;

public interface AdminUserService {
    AdminUserDO getUserById(Long userId);
}
