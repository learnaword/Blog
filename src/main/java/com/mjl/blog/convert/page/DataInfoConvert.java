package com.mjl.blog.convert.page;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.data.vo.TableRespVO;
import com.mjl.blog.dal.dataobject.ButtonInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface DataInfoConvert {
    DataInfoConvert INSTANCE = Mappers.getMapper(DataInfoConvert.class);

    PageResult<TableRespVO> convert(PageResult<ButtonInfoDO> buttonInfoList);

    TableRespVO convert2(ButtonInfoDO item);
}
