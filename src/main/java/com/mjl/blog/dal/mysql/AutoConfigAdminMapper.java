package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.config.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoConfigAdminMapper extends BaseMapperX<AutoConfigDO> {

    //根据username获取到用户信息
    default PageResult<AutoConfigDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<AutoConfigDO>()
                .eq(AutoConfigDO::getStatus, reqVO.getStatus())
                .orderByDesc(AutoConfigDO::getUpdateTime));
    }
}
