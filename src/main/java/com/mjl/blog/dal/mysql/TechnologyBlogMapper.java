package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.technology.blog.vo.BlogTableReqVO;
import com.mjl.blog.controller.page.technology.blog.vo.IndexReqVO;
import com.mjl.blog.controller.page.technology.soft.vo.SoftDetailTypeReqVO;
import com.mjl.blog.controller.page.technology.soft.vo.SoftDetailsReqVO;
import com.mjl.blog.dal.dataobject.TechnologyBlogDO;
import com.mjl.blog.enums.BlogStatusEnum;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TechnologyBlogMapper extends BaseMapperX<TechnologyBlogDO> {

    //根据username获取到用户信息
    default PageResult<TechnologyBlogDO> selectPage(BlogTableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologyBlogDO>()
                .eq(TechnologyBlogDO::getStatus, reqVO.getStatus())
                .orderByDesc(TechnologyBlogDO::getUpdateTime));
    }

    default PageResult<TechnologyBlogDO> selectPage(IndexReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologyBlogDO>()
                .eq(TechnologyBlogDO::getStatus, BlogStatusEnum.PUBLISHED.getStatus())
                .orderByDesc(TechnologyBlogDO::getCreateTime));
    }

    default PageResult<TechnologyBlogDO> selectPage(SoftDetailsReqVO reqVO, Long softId){
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologyBlogDO>()
                .eq(TechnologyBlogDO::getStatus, BlogStatusEnum.PUBLISHED.getStatus())
                .eq(TechnologyBlogDO::getSoftId,softId).orderByDesc(TechnologyBlogDO::getUpdateTime));
    }

    default PageResult<TechnologyBlogDO> selectPage(SoftDetailTypeReqVO reqVO, Long softId, Integer softSection){
        return selectPage(reqVO, new LambdaQueryWrapper<TechnologyBlogDO>()
                .eq(TechnologyBlogDO::getStatus, BlogStatusEnum.PUBLISHED.getStatus())
                .eq(TechnologyBlogDO::getSoftId,softId).eq(TechnologyBlogDO::getSoftSection,softSection)
                .orderByDesc(TechnologyBlogDO::getUpdateTime));
    };
}
