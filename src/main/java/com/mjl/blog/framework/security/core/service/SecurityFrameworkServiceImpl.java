package com.mjl.blog.framework.security.core.service;

import cn.hutool.core.collection.CollUtil;
import com.mjl.blog.framework.security.core.LoginUser;
import com.mjl.blog.framework.security.core.util.SecurityFrameworkUtils;
import lombok.AllArgsConstructor;

import java.util.Arrays;

import static com.mjl.blog.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

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
