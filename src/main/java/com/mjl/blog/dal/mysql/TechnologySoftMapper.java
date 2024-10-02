package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.soft.vo.TableReqVO;
import com.mjl.blog.controller.page.technology.soft.vo.*;
import com.mjl.blog.dal.dataobject.TechnologySoftDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TechnologySoftMapper extends BaseMapperX<TechnologySoftDO> {
    default PageResult<TechnologySoftDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologySoftDO>()
                .eq(TechnologySoftDO::getStatus, reqVO.getStatus())
                .orderByDesc(TechnologySoftDO::getUpdateTime));
    }

    default PageResult<TechnologySoftDO> selectPage(SoftReqVO softReqVO){
        return selectPage(softReqVO, new LambdaQueryWrapper<TechnologySoftDO>()
                .eq(TechnologySoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc(TechnologySoftDO::getUpdateTime));
    };

    default PageResult<TechnologySoftDO> selectPage(SoftInfoReqVO reqVO, Long typeId){
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologySoftDO>()
                .eq(TechnologySoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .eq(TechnologySoftDO::getTypeId, typeId)
                .orderByDesc(TechnologySoftDO::getUpdateTime));
    };
}
