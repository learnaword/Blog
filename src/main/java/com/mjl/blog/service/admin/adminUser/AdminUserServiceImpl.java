package com.mjl.blog.service.admin.adminUser;

import com.mjl.blog.dal.dataobject.AdminUserDO;
import com.mjl.blog.dal.mysql.AdminUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    AdminUserMapper adminUserMapper;
    @Override
    public AdminUserDO getUserById(Long userId) {
        return adminUserMapper.selectOne(AdminUserDO::getId,userId);
    }
}
