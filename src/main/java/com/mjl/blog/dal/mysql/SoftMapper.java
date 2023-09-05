package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.SoftDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SoftMapper extends BaseMapperX<SoftDO> {
    default PageResult<SoftDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<SoftDO>()
                .eq(SoftDO::getStatus, reqVO.getStatus())
                .orderByDesc(SoftDO::getUpdateTime));
    }
}
