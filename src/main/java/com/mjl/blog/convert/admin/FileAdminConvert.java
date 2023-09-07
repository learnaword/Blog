package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.file.vo.GetRespVO;
import com.mjl.blog.controller.admin.file.vo.TableRespVO;
import com.mjl.blog.controller.admin.file.vo.UpdateReqVO;
import com.mjl.blog.dal.dataobject.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileAdminConvert {
    FileAdminConvert INSTANCE = Mappers.getMapper(FileAdminConvert.class);

    PageResult<TableRespVO> convert(PageResult<FileDO> list);

    GetRespVO convert2(FileDO byId);

    FileDO convert(UpdateReqVO updateReqVO);
}
