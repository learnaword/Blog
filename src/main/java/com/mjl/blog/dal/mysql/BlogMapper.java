package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.blog.vo.BlogTableReqVO;
import com.mjl.blog.controller.page.blog.vo.IndexReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailTypeReqVO;
import com.mjl.blog.controller.page.soft.vo.SoftDetailsReqVO;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.enums.BlogStatusEnum;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapperX<BlogDO> {

    //根据username获取到用户信息
    default PageResult<BlogDO> selectPage(BlogTableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<BlogDO>()
                .eq(BlogDO::getStatus, reqVO.getStatus())
                .orderByDesc(BlogDO::getUpdateTime));
    }

    default PageResult<BlogDO> selectPage(IndexReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<BlogDO>()
                .eq(BlogDO::getStatus, BlogStatusEnum.PUBLISHED.getStatus())
                .orderByDesc(BlogDO::getCreateTime));
    }

    default PageResult<BlogDO> selectPage(SoftDetailsReqVO reqVO, Long softId){
        return selectPage(reqVO, new LambdaQueryWrapper<BlogDO>()
                .eq(BlogDO::getStatus, BlogStatusEnum.PUBLISHED.getStatus())
                .eq(BlogDO::getSoftId,softId).orderByDesc(BlogDO::getUpdateTime));
    }

    default PageResult<BlogDO> selectPage(SoftDetailTypeReqVO reqVO, Long softId, Integer softSection){
        return selectPage(reqVO, new LambdaQueryWrapper<BlogDO>()
                .eq(BlogDO::getStatus, BlogStatusEnum.PUBLISHED.getStatus())
                .eq(BlogDO::getSoftId,softId).eq(BlogDO::getSoftSection,softSection)
                .orderByDesc(BlogDO::getUpdateTime));
    };
}
