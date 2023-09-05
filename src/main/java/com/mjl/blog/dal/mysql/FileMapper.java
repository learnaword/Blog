package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.file.vo.*;
import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper extends BaseMapperX<FileDO> {
    default PageResult<FileDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<FileDO>()
                .eq(FileDO::getStatus, reqVO.getStatus())
                .orderByDesc(FileDO::getUpdateTime));
    }
}
