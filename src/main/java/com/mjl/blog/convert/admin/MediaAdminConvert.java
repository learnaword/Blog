package com.mjl.blog.convert.admin;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.media.vo.*;
import com.mjl.blog.dal.dataobject.MediaDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MediaAdminConvert {
    MediaAdminConvert INSTANCE = Mappers.getMapper(MediaAdminConvert.class);

    PageResult<TableRespVO> convert(PageResult<MediaDO> mediaList);

    MediaDO convert(CreateReqVO createReqVO);

    MediaRespVO convert(MediaDO mediaById);

    MediaDO convert(UpdateReqVO updateReqVO);
}
