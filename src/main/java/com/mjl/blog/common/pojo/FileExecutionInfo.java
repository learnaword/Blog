package com.mjl.blog.common.pojo;

import lombok.Data;

@Data
public class FileExecutionInfo {
    private String fileId; // 文件标识符
    private int executionCount; // 执行数量

    private int total; // 执行数量

    public FileExecutionInfo(String fileId) {
        this.fileId = fileId;
        this.executionCount = 0;
    }

    public void executionCountIncrease(int num){
        this.executionCount++;
    }

    public double getPercentage() {
        return  (executionCount * 1.0 / total) * 100;
    }
}
