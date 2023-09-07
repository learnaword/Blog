package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.type.vo.*;
import com.mjl.blog.dal.dataobject.TypeDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeMapper extends BaseMapperX<TypeDO> {
    default PageResult<TypeDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<TypeDO>()
                .eq(TypeDO::getStatus, reqVO.getStatus())
                .orderByDesc(TypeDO::getUpdateTime));
    }
}
