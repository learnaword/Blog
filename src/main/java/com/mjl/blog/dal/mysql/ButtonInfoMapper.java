package com.mjl.blog.dal.mysql;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.page.data.vo.TableReqVO;
import com.mjl.blog.dal.dataobject.ButtonInfoDO;
import com.mjl.blog.framework.mybatis.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ButtonInfoMapper extends BaseMapperX<ButtonInfoDO> {
    default PageResult<ButtonInfoDO> selectPage(TableReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<ButtonInfoDO>()
                .orderByDesc(ButtonInfoDO::getCreateTime));
    }
}
