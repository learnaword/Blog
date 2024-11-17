## 项目简介
博客主要是为了分享一些个人的生活经验，网上创业，人生感悟，解决生活中遇到的小问题。主要包括：博客管理、自媒体、技术专区等。

github地址：https://github.com/learnaword/Blog

![](https://www.bangmangma.com/upload/blog/28e21c25a70e3a8a54ccb9c60692c7584acf9531f4ca964f8029650366df9c8e.png)

## 项目整体
项目整体包含四个模块：博客管理、技术专区、自媒体、基础设施。

1、JAVA后端：JDK20 + Spring Boot 3.0.6

2、管理界面和前端界面：Jsp + Layui

3、后端采用Spring Boot、MySQL + MyBatis Plus、Redis

4、数据库使用的是MySQL

5、权限认证使用 Spring Security & Token & Redis

6、基础设施：Swagger接口文档、基于Screw自动生成数据库文档、Quarz定任务、EChar报表展示、基于AOP操作日志、JAVA监控Spring Boot Admin。

## 项目复杂度分析
性能：因为百度要求每个网页的响应速度必须在1秒以内，由于服务器的配置不高（2核、8G），所以需要对性能进行优化。

### 优化方法：

1、tomcat优化

2、nginx优化

3、添加Redis缓存

4、JVM优化

5、代码逻辑优化

6、前端js引用第三方的。


## 核心功能
文章自动生成

TODO
