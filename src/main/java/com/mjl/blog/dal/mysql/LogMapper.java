package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.log.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.LogDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapperX<LogDO> {
    default PageResult<LogDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<LogDO>()
                .orderByDesc(LogDO::getCreateTime));
    }
}
