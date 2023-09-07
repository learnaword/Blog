package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.GetRespVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.TableRespVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateReqVO;
import com.mjl.blog.dal.dataobject.SentenceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SentenceAdminConvert {
    SentenceAdminConvert INSTANCE = Mappers.getMapper(SentenceAdminConvert.class);

    PageResult<TableRespVO> convert(PageResult<SentenceDO> list);

    GetRespVO convert2(SentenceDO sentenceDO);

    SentenceDO convert(UpdateReqVO updateReqVO);
}
