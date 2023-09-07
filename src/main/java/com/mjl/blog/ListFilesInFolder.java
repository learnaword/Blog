package com.mjl.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mjl.blog.common.enums.CommonStatusEnum;
import com.mjl.blog.dal.dataobject.BlogDO;
import com.mjl.blog.dal.dataobject.MediaDO;
import com.mjl.blog.dal.mysql.BlogMapper;
import com.mjl.blog.dal.mysql.MediaMapper;
import com.mjl.blog.enums.BlogStatusEnum;
import com.mjl.blog.enums.FileStatusEnum;
import com.mjl.blog.service.admin.blog.BlogAdminService;
import com.mjl.blog.service.admin.file.FileAdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.mjl.blog.task.FileInfo;

@SpringBootApplication
public class ListFilesInFolder {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(ListFilesInFolder.class, args);

        BlogMapper blogMapper = context.getBean(BlogMapper.class);
        MediaMapper mediaMapper = context.getBean(MediaMapper.class);

        //从数据库里获取到微头条的文章。
        List<BlogDO> blogDOList = blogMapper.selectList(new QueryWrapper<BlogDO>().eq("soft_id",122)
                .eq("status",1));
        //将文章插入到微头条数据库里。
        for (BlogDO blogDO :blogDOList) {
            MediaDO mediaDO = new MediaDO().setId(blogDO.getId()).setIntroduction(blogDO.getIntroduction())
                    .setContent(blogDO.getContent()).setImages(blogDO.getImages()).setKeyword(blogDO.getKeyword())
                    .setIsTop(blogDO.getIsTop()).setTitle(blogDO.getTitle()).setStatus(CommonStatusEnum.ENABLE.getStatus())
                    .setCreateTime(blogDO.getCreateTime()).setUpdateTime(blogDO.getUpdateTime()).setRankScore(blogDO.getRankScore());
            mediaMapper.insert(mediaDO);
            blogDO.setStatus(BlogStatusEnum.SPAM.getStatus());
            blogMapper.updateById(blogDO);

        }

    }


/*
    public void insertBlogImage () {
        ConfigurableApplicationContext context = SpringApplication.run(ListFilesInFolder.class, args);
        FileAdminService fileAdminService = context.getBean(FileAdminService.class);
        BlogAdminService blogAdminService = context.getBean(BlogAdminService.class);

        String folderPath = "/Users/majunliang/Downloads/blogTemp/blog"; // 将此路径替换为你要操作的文件夹路径
        File folder = new File(folderPath);
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            // 打印文件列表
            for (File file : files) {
                Boolean isBreak = false;
                Path path = Paths.get(file.getPath());
                String filePath = file.getPath();
                String fileType = Files.probeContentType(path);
                String name = file.getName();
                byte[] fileContent = Files.readAllBytes(path);
                //这个图片是否在其它文件里用过，使用过返回true，没使用过返回false
                if (blogAdminService.fileIsUse(name)) {
                    for (FileInfo item : fileInfoList) {
                        //文件是否重复
                        if (Arrays.equals(fileContent, item.getFileContent())) {
                            //将文章里重复的url替换掉。将A替换为B/upload/blog/2020-10-31734181.PNG
                            blogAdminService.replace(name, item.getName());
                            isBreak = true;
                            break;
                        }
                    }
                } else {
                    //没用过
                    isBreak = true;
                }
                //不重复就添加
                if (!isBreak) {
                    FileInfo fileInfo = new FileInfo().setFileType(fileType)
                            .setFileContent(fileContent).setFilePath(filePath)
                            .setName(name);

                    fileInfoList.add(fileInfo);
                }
            }

            System.out.println(fileInfoList.size());
            for (FileInfo item : fileInfoList) {
                String name = item.getName(); // 将文件名替换为实际的文件名
                byte[] fileContent = item.getFileContent();// 替换为实际的文件内容字节数组

                //文件插入到数据库里
                fileAdminService.upload(name, name, fileContent, FileStatusEnum.BLOG_FILE.getStatus());
                System.out.println("文件保存成功------------");
            }
        } else {
            System.err.println("指定路径不是一个文件夹。");
        }
    }*/
}
