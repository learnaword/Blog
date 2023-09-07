package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.soft.vo.TableReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftInfoReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftReqVO;
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

    default PageResult<SoftDO> selectPage(SoftReqVO softReqVO){
        return selectPage(softReqVO, new LambdaQueryWrapper<SoftDO>()
                .eq(SoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc(SoftDO::getUpdateTime));
    };

    default PageResult<SoftDO> selectPage(SoftInfoReqVO reqVO, Long typeId){
        return selectPage(reqVO, new LambdaQueryWrapper<SoftDO>()
                .eq(SoftDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .eq(SoftDO::getTypeId, typeId)
                .orderByDesc(SoftDO::getUpdateTime));
    };
}
