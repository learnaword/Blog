package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.SentenceDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SentenceMapper extends BaseMapperX<SentenceDO> {
    default PageResult<SentenceDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<SentenceDO>()
                .eq(SentenceDO::getStatus, reqVO.getStatus())
                .orderByDesc(SentenceDO::getUpdateTime));
    }
}
