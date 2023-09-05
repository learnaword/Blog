package com.mjl.blog.service.admin.file;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mjl.blog.common.utils.FileUtils;
import com.mjl.blog.dal.dataobject.FileDO;
import com.mjl.blog.dal.mysql.FileMapper;
import com.mjl.blog.framework.file.utils.FileTypeUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileServiceImpl implements FileService{
    @Resource
    private FileMapper fileMapper;

    @Override
    public String upload(String name, String path, byte[] content) {
        // 计算默认的 path 名
        String type = FileTypeUtils.getMineType(content, name);
        if (StrUtil.isEmpty(path)) {
            path = FileUtils.generatePath(content, name);
        }
        // 如果 name 为空，则使用 path 填充
        if (StrUtil.isEmpty(name)) {
            name = path;
        }

        String url = formatFileUrl(path);
        List<FileDO> fileDOS = fileMapper.selectList("path",path);

        if(fileDOS.isEmpty()) {
            // 保存到数据库
            FileDO file = new FileDO();
            file.setName(name);
            file.setPath(path);
            file.setContent(content);
            file.setUrl(url);
            file.setType(type);
            file.setSize(content.length);
            fileMapper.insert(file);
        }

        return url;
    }

    @Override
    public FileDO getByPath(String path) {
        List<FileDO> list = fileMapper.selectList("path",path);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void delete(String path) {
        fileMapper.delete(new QueryWrapper<FileDO>().eq("path",path));
    }

    @Override
    public byte[] getContent(String path) {
        List<FileDO> list = fileMapper.selectList("path",path);
        if(!list.isEmpty()){
            return list.get(0).getContent();
        }
        return null;
    }

    protected String formatFileUrl(String path) {
        return StrUtil.format("/admin-api/infra/file/get/{}", path);
    }


}
