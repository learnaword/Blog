package com.mjl.blog.common.utils;

import cn.hutool.core.io.IoUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 客户端工具类
 *
 */
public class ServletUtils {

    /**
     * 返回附件，这是下载
     *在浏览器输入url，会直接下载图片。
     *
     * @param response 响应
     * @param filename 文件名
     * @param content 附件内容
     */
    public static void writeAttachment(HttpServletResponse response, String filename, byte[] content,String filetype) throws IOException {
        // 设置 header 和 contentType
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        // 输出附件
        IoUtil.write(response.getOutputStream(), false, content);
    }

    /**
     * 返回附件，这是展示
     * 在浏览器输入url，会直接展示图片。
     * @param response 响应
     * @param content 附件内容
     * @param fileType 文件类型
     */
    public static void write(HttpServletResponse response, byte[] content, String fileType) throws IOException {
        response.setHeader("Content-Disposition", "inline");
        response.setContentType(fileType); // 或者根据实际图像类型设置Content-Type
        // 输出附件
        IoUtil.write(response.getOutputStream(), false, content);
    }
}
