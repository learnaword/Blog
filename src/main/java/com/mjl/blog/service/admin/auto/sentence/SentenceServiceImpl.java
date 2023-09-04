package com.mjl.blog.service.admin.auto.sentence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.TableReqVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateReqVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateStatusReqVO;
import com.mjl.blog.convert.AutoConfigConvert;
import com.mjl.blog.convert.SentenceConvert;
import com.mjl.blog.dal.dataobject.AutoConfigDO;
import com.mjl.blog.dal.dataobject.SentenceDO;
import com.mjl.blog.dal.mysql.SentenceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceServiceImpl implements SentenceService{
    @Resource
    SentenceMapper sentenceMapper;
    @Override
    public PageResult<SentenceDO> getList(TableReqVO tableReqVO) {
        return sentenceMapper.selectPage(tableReqVO);
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<SentenceDO> autoConfigDOList =  sentenceMapper.selectList(new LambdaQueryWrapper<SentenceDO>().in(SentenceDO::getId,updateStatusReqVO.getIds()));
        autoConfigDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        sentenceMapper.updateBatch(autoConfigDOList);
    }

    @Override
    public SentenceDO getById(Long id) {
        return sentenceMapper.selectById(id);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        SentenceDO sentenceDO = SentenceConvert.INSTANCE.convert(updateReqVO);
        sentenceDO.setUpdateTime(System.currentTimeMillis());
        sentenceMapper.updateById(sentenceDO);
    }
}
