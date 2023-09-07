package com.mjl.blog.service.admin.auto.sentence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.controller.admin.auto.Sentence.vo.TableReqVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateReqVO;
import com.mjl.blog.controller.admin.auto.Sentence.vo.UpdateStatusReqVO;
import com.mjl.blog.convert.admin.SentenceAdminConvert;
import com.mjl.blog.dal.dataobject.SentenceDO;
import com.mjl.blog.dal.mysql.SentenceAdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentenceAdminServiceImpl implements SentenceAdminService {
    @Resource
    private SentenceAdminMapper sentenceMapper;

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
        SentenceDO sentenceDO = SentenceAdminConvert.INSTANCE.convert(updateReqVO);
        sentenceDO.setUpdateTime(System.currentTimeMillis());
        sentenceMapper.updateById(sentenceDO);
    }

    @Override
    public void fileCreate(byte[] content, String path, String name) {

        String fileContent = new String(content);
        // 按行拆分文件内容
        String[] lines = fileContent.split("\\n");
        for (String line : lines) {
            // 在这里处理每一行的数据
            String[] parts = line.split(",");
            if(parts.length>0) {
                List<SentenceDO> sentenceDOList = sentenceMapper.selectList(SentenceDO::getContent, parts[0]);
                if (sentenceDOList.isEmpty()) {
                    SentenceDO sentenceDO = new SentenceDO().setUpdateTime(System.currentTimeMillis())
                            .setCreateTime(System.currentTimeMillis()).setContent(parts[0]).setUsages(0)
                            .setStatus(CommonStatusEnum.ENABLE.getStatus());
                    sentenceMapper.insert(sentenceDO);
                }
            }
        }
    }

    @Override
    public List<SentenceDO> getListLimit(int i) {
        return sentenceMapper.selectList(new LambdaQueryWrapper<SentenceDO>().eq(SentenceDO::getStatus,CommonStatusEnum.ENABLE.getStatus())
                .lt(SentenceDO::getUsages,3).last("ORDER BY RAND() LIMIT 2"));
    }

    @Override
    public void updateList(List<SentenceDO> sentenceDOList) {
        sentenceDOList.forEach(item->item.setUsages(item.getUsages()+1)
                .setUpdateTime(System.currentTimeMillis()));
        sentenceMapper.updateBatch(sentenceDOList);
    }
}
