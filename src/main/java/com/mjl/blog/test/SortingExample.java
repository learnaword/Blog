package com.mjl.blog.test;

import java.util.*;

public class SortingExample {
    public static void main(String[] args) {
        List<Integer> idList = Arrays.asList(126, 129, 128, 127);

        // 模拟从数据库中查询出的数据
        List<DataItem> dataList = Arrays.asList(
                new DataItem(127, "Data 127"),
                new DataItem(128, "Data 128"),
                new DataItem(126, "Data 126"),
                new DataItem(129, "Data 129")
        );

        // 使用 Collections.sort 进行排序
        Collections.sort(dataList, Comparator.comparingInt(item -> idList.indexOf(item.getId())));

        // 输出排序后的结果
        System.out.println("排序后的结果:");
        for (DataItem item : dataList) {
            System.out.println(item);
        }
    }

    static class DataItem {
        private int id;
        private String data;

        public DataItem(int id, String data) {
            this.id = id;
            this.data = data;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "DataItem{" +
                    "id=" + id +
                    ", data='" + data + '\'' +
                    '}';
        }
    }
}
