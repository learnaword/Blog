package com.mjl.blog.service.admin.file;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.common.pojo.PageResult;
import com.mjl.blog.common.utils.FileUtils;
import com.mjl.blog.controller.admin.file.vo.*;
import com.mjl.blog.convert.admin.FileAdminConvert;
import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.dal.dataobject.MediaDO;
import com.mjl.blog.dal.mysql.FileAdminMapper;
import com.mjl.blog.enums.FileStatusEnum;
import com.mjl.blog.framework.file.utils.FileTypeUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjl.blog.common.exception.utils.ServiceExceptionUtil.exception;
import static com.mjl.blog.enums.ErrorCodeConstants.*;

@Service
public class FileAdminServiceImpl implements FileAdminService {
    @Resource
    private FileAdminMapper fileMapper;

    @Override
    public String upload(String name, String path, byte[] content,Integer module) {
        // 计算默认的 path 名
        String type = FileTypeUtils.getMineType(content, name);
        if (StrUtil.isEmpty(path)) {
            path = FileUtils.generatePath(content, name);
        }
        // 如果 name 为空，则使用 path 填充
        if (StrUtil.isEmpty(name)) {
            name = path;
        }

        String url = formatFileUrl(path,module);
        List<FileDO> fileDOS = fileMapper.selectList(new QueryWrapper<FileDO>().eq("path",path)
                .eq("module",module));

        if (fileDOS.isEmpty()) {
            // 保存到数据库
            FileDO file = new FileDO()
                    .setName(name)
                    .setPath(path)
                    .setContent(content)
                    .setUrl(url)
                    .setType(type)
                    .setSize(content.length)
                    .setStatus(CommonStatusEnum.ENABLE.getStatus())
                    .setCreateTime(System.currentTimeMillis())
                    .setUpdateTime(System.currentTimeMillis())
                    .setModule(module);
            fileMapper.insert(file);
        }

        return url;
    }

    @Override
    public FileDO getByPath(String path, Integer module) {
        List<FileDO> list = fileMapper.selectList(new QueryWrapper<FileDO>().eq("path", path)
                .eq("module", module).eq("status", CommonStatusEnum.ENABLE.getStatus()));
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void delete(String path) {
        fileMapper.delete(new QueryWrapper<FileDO>().eq("path", path));
    }

    @Override
    public byte[] getContent(String path) {
        List<FileDO> list = fileMapper.selectList("path", path);
        if (!list.isEmpty()) {
            return list.get(0).getContent();
        }
        return null;
    }

    @Override
    public void updateStatus(UpdateStatusReqVO updateStatusReqVO) {
        List<FileDO> fileDOList = fileMapper.selectList(new LambdaQueryWrapper<FileDO>().in(FileDO::getId, updateStatusReqVO.getIds()));
        fileDOList.forEach(item -> item.setStatus(updateStatusReqVO.getStatus()).setUpdateTime(System.currentTimeMillis()));
        fileMapper.updateBatch(fileDOList);
    }

    @Override
    public PageResult<FileDO> getList(TableReqVO tableReqVO) {
        return fileMapper.selectPage(tableReqVO);
    }

    @Override
    public FileDO getById(Long id) {
        return fileMapper.selectById(id);
    }

    @Override
    public void update(UpdateReqVO updateReqVO) {
        FileDO fileDO = FileAdminConvert.INSTANCE.convert(updateReqVO);
        fileDO.setUpdateTime(System.currentTimeMillis());
        fileDO.setUrl(formatFileUrl(fileDO.getPath(), fileDO.getModule()));

        List<FileDO> fileDOS = fileMapper.selectList(new LambdaQueryWrapper<FileDO>().eq(FileDO::getUrl,fileDO.getUrl())
                .eq(FileDO::getStatus,CommonStatusEnum.ENABLE.getStatus())
                .notIn(FileDO::getId,updateReqVO.getId()));
        //path冲突
        if(!fileDOS.isEmpty()){
            throw exception(PATH_OCCUPIED);
        }
        fileMapper.updateById(fileDO);
    }

    @Override
    public void updateContent(String name, String path, byte[] content, Long id) {

        FileDO fileDO = fileMapper.selectById(id);
        if(fileDO==null){
            //更新的文件不存在
            throw exception(FILE_NOT_FOUND);
        }
        String type = FileTypeUtils.getMineType(content, name);
        if(!fileDO.getType().equals(type)){
            //文件类型不匹配
            throw exception(FILE_TYPE_MATCH);
        }

        // 保存到数据库
        FileDO file = new FileDO()
                .setId(id)
                .setContent(content)
                .setSize(content.length)
                .setUpdateTime(System.currentTimeMillis());
        fileMapper.updateById(file);
    }

    @Override
    public void updateModule(UpdateModuleReqVO updateModuleReqVO) {
        List<FileDO> fileDOList =  fileMapper.selectList(new LambdaQueryWrapper<FileDO>().in(FileDO::getId,updateModuleReqVO.getIds()));
        fileDOList.forEach(item -> item.setModule(updateModuleReqVO.getModule()).setUpdateTime(System.currentTimeMillis()));
        fileMapper.updateBatch(fileDOList);
    }

    protected String formatFileUrl(String path, Integer module) {

        if (module.equals(FileStatusEnum.SYSTEM_FILE.getStatus())) {
            return StrUtil.format("/images/{}", path);
        } else if (module.equals(FileStatusEnum.BLOG_FILE.getStatus())) {
            return StrUtil.format("/upload/blog/{}", path);
        } else if (module.equals(FileStatusEnum.BACKGROUND_FILE.getStatus())) {
            return StrUtil.format("/upload/background/{}", path);
        } else if (module.equals(FileStatusEnum.COMMON_FILE.getStatus())) {
            return StrUtil.format("/upload/common/{}", path);
        }else{
            return StrUtil.format("/upload/common/{}", path);
        }

    }
}
