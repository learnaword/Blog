package com.mjl.blog.task;

import lombok.Data;

@Data
public class FileInfo {
    private String filePath;
    private String fileType;
    private String name;
    private byte[] fileContent;
}
