package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.recommend.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.RecommendDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendMapper extends BaseMapperX<RecommendDO> {
    default PageResult<RecommendDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<RecommendDO>()
                .eq(RecommendDO::getStatus, reqVO.getStatus())
                .orderByDesc(RecommendDO::getUpdateTime));
    }
}
