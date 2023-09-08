package com.mjl.blog.common.utils;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.IoUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;

/**
 * 客户端工具类
 *
 */
public class ServletUtils {

    /**
     * 返回 JSON 字符串
     *
     * @param response 响应
     * @param object 对象，会序列化成 JSON 字符串
     */
    @SuppressWarnings("deprecation") // 必须使用 APPLICATION_JSON_UTF8_VALUE，否则会乱码
    public static void writeJSON(HttpServletResponse response, Object object) {
        String content = JsonUtils.toJsonString(object);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(content);
            writer.flush();
        } catch (IOException var8) {
            throw new UtilException(var8);
        } finally {
            IoUtil.close(writer);
        }
    }


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
