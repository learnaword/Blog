package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.media.vo.*;
import com.mjl.blog.controller.page.media.vo.MediaReqVO;
import com.mjl.blog.dal.dataobject.MediaDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MediaMapper extends BaseMapperX<MediaDO> {

    //根据username获取到用户信息
    default PageResult<MediaDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<MediaDO>()
                .eq(MediaDO::getStatus, reqVO.getStatus())
                .orderByDesc(MediaDO::getUpdateTime));
    }

    default PageResult<MediaDO> selectPage(MediaReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<MediaDO>()
                .eq(MediaDO::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByDesc(MediaDO::getCreateTime));
    }
}
