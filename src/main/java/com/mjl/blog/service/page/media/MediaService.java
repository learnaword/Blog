package com.mjl.blog.service.page.media;

import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.media.vo.*;
import com.mjl.blog.dal.dataobject.MediaDO;

import java.util.List;

public interface MediaService {
    PageResult<MediaDO> getNewList(MediaReqVO mediaReqVO);

    List<TopMediasRespVO> getTopMedias();

    List<MediaDO> getNewMedias();

    MediaDO selectMediaById(Long id);

    List<RelMediasRespVO> getRelMedias(MediaDO media);

    List<HotMediasRespVO> getHotMedias(MediaDO media);

    MediaDO selectNextMedia(Long id);

    MediaDO selectPrevMedia(Long id);
}
