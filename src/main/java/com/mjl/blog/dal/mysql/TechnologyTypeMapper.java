package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.type.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.TechnologyTypeDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TechnologyTypeMapper extends BaseMapperX<TechnologyTypeDO> {
    default PageResult<TechnologyTypeDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologyTypeDO>()
                .eq(TechnologyTypeDO::getStatus, reqVO.getStatus())
                .orderByDesc(TechnologyTypeDO::getUpdateTime));
    }
}
