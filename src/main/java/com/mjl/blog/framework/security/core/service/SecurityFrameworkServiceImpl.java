package com.mjl.blog.framework.security.core.service;

import com.mjl.blog.framework.security.core.LoginUser;
import com.mjl.blog.framework.security.core.util.SecurityFrameworkUtils;
import lombok.AllArgsConstructor;

/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 */
@AllArgsConstructor
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {

    @Override
    public boolean hasPermissions() {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return false;
        }

        if(user.isHasPermission()){
            return true;
        }

        return false;
    }
}
