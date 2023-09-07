package com.mjl.blog.convert.page;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.blog.vo.NewListRespVO;
import com.mjl.blog.controller.page.blog.vo.NextAndPreBlogRespVO;
import com.mjl.blog.controller.page.media.vo.*;
import com.mjl.blog.dal.dataobject.MediaDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = DateMapper.class)
public interface MediaConvert {
    MediaConvert INSTANCE = Mappers.getMapper(MediaConvert.class);

    List<NewMediasRespVO> convertNew(List<MediaDO> newBlogs);
    PageResult<NewListRespVO> covertNew(PageResult<MediaDO> newList);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    NewListRespVO covertNew (MediaDO mediaDO);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    MediaInfoRespVO convert(MediaDO media);

    NextAndPreBlogRespVO convertNextAndPre(MediaDO selectNextMedia);

    List<HotMediasRespVO> convertHot(List<MediaDO> mediaDOList);

    List<TopMediasRespVO> convertRecommend(List<MediaDO> mediaDOList);

    List<RelMediasRespVO> convertRel(List<MediaDO> mediaDOList);
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "timestampToString")
    RelMediasRespVO convertRel(MediaDO mediaDO);
}
