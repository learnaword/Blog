package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.log.vo.TableRespVO;
import com.mjl.blog.dal.dataobject.LogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LogConvert {
    LogConvert INSTANCE =  Mappers.getMapper(LogConvert.class);

    PageResult<TableRespVO> convert(PageResult<LogDO> list);
}
