package com.mjl.blog.convert;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.file.vo.TableRespVO;
import com.mjl.blog.dal.dataobject.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {
    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    PageResult<TableRespVO> convert(PageResult<FileDO> list);
}
