package com.mjl.blog.convert.page;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.soft.vo.SoftDetailTypeRespVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailsRespVO;
import com.mjl.blog.controller.page.soft.vo.SoftInfoRespVO;
import com.mjl.blog.controller.page.soft.vo.SoftListRespVO;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.SoftDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface SoftConvert {
    SoftConvert INSTANCE = Mappers.getMapper(SoftConvert.class);

    PageResult<SoftListRespVO> covert(PageResult<SoftDO> list);

    PageResult<SoftDetailsRespVO> covertDetails(PageResult<BlogDO> softDetails);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    SoftDetailsRespVO covertDetails(BlogDO blogDO);

    PageResult<SoftInfoRespVO> covertInfo(PageResult<SoftDO> softInfos);

    PageResult<SoftDetailTypeRespVO> covertDetailType(PageResult<BlogDO> softDetailTypeBlogs);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    SoftDetailTypeRespVO covertDetailType(BlogDO blogDO);
}
