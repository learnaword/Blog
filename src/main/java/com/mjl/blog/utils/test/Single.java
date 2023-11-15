package com.mjl.blog.utils.test;

public class Single {

    public static void main(String[] args) {
        int totalBlogs = 400;
        int days = 0;

        while (totalBlogs > 0) {
            int blogsToUpdateA = Math.max(totalBlogs / 70, 1);
            int blogsToUpdateD = Math.max(totalBlogs / 70, 0);
            totalBlogs -= (blogsToUpdateA + blogsToUpdateD);
            System.out.println("第" + days + " 天更新了："+(blogsToUpdateA + blogsToUpdateD));
            days++;
        }

        System.out.println("需要 " + days + " 天才能将总数量变为0。");
    }

}
