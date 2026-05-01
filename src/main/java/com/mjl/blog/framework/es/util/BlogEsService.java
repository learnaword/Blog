package com.mjl.blog.framework.es.util;

import com.mjl.blog.framework.es.entity.BlogEs;
import com.mjl.blog.framework.es.mapper.BlogEsMappser;
import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogEsService {
    @Autowired
    private BlogEsMappser blogEsMappser;

    public EsPageInfo<BlogEs> testFindByNameAndStateWithEasyEs(){
        LambdaEsQueryWrapper<BlogEs> queryWrapper = new LambdaEsQueryWrapper<>();
        queryWrapper.match(BlogEs::getTitle, "测试")
                .and(wrapper -> wrapper
                        .match(BlogEs::getStatus, "SUCCEED")
                        .match(BlogEs::getDeleted, "0"))
                .orderByAsc("collection_id");
        EsPageInfo<BlogEs> results = blogEsMappser.pageQuery(queryWrapper, 1, 1);
        return results;
    }

    /**
     * 根据关键词搜索博客，搜索title和content，title权重占3，排除已删除的博客
     * @param keyword 搜索关键词
     * @return 搜索结果列表
     */
    public List<BlogEs> searchByKeyword(String keyword) {
        LambdaEsQueryWrapper<BlogEs> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(BlogEs::getTitle, keyword, 3.0f)
               .or()
               .match(BlogEs::getContent, keyword)
               .eq(BlogEs::getDeleted, 0);
        return blogEsMappser.selectList(wrapper);
    }
}
