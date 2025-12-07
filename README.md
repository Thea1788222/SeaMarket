# 📦 SeaMarket —— 海鲜市场-二手交易平台

**海鲜市场**：
基于 **Java Web + 自行封装数据库访问层 + MySQL** 的二手交易平台课程项目。

---

## 🐳 项目简介

SeaMarket 是一个面向校园/小型社区的在线二手交易平台，支持用户发布商品、浏览、搜索、收藏、购买等功能。
本项目重点展示 **数据库设计、DAO 层封装、事务处理、分页查询、文件上传、简单缓存** 等能力。

本 README 用于指导如何运行、如何理解本项目的架构与代码结构。

---

## 🚀 功能概览

### **用户系统**

* 用户注册 / 登录
* 密码加密存储
* 用户权限（普通用户，后期课加入管理员）

### **商品管理**

* 发布商品（标题、描述、价格、（图片））
* （多图上传）
* 商品列表分页展示
* 商品搜索（模糊搜索）
* 商品详情页
* 商品状态（在售 / 已售 / 已删除）

### **交易系统**

* 下单购买
* 使用事务保证“创建订单 + 修改商品状态”一致性
* 乐观锁防止并发购买同一件商品

### **用户交互**

* 收藏/取消收藏
* 我的收藏列表
* 我的订单

### **后台功能（可选）**

* 分类管理
* 日志查看
* 用户管理

---

## 🔧 技术栈

| 领域     | 使用技术                                          |
| ------ | --------------------------------------------- |
| 语言     | Java 17+                                       |
| Web 框架 | Servlet           |
| 前端     | HTML / CSS / JS |
| 数据库    | MySQL 5.7+/8                                  |
| 数据库访问  | 自行封装的 DAO 层       |
| 构建工具   | Maven                     |
| 存储     | 本地文件系统上传图片（uploads/）                          |
| 其他     | BCrypt、分页、事务、日志                               |



## 🗂️ 项目结构

```text
SeaMarket/
│
├── src/main/java/
│   ├── db/                # 底层数据库封装（ConnectionManager, DbExecutor, TransactionManager）
│   ├── dao/               # DAO 接口
│   ├── dao/impl/          # DAO 实现类
│   ├── model/             # 实体类（User, Item, Order...）
│   ├── service/           # 业务逻辑层
│   ├── controller/               # 控制器（Servlet / Controller）
│   └── util/              # 工具类（密码、校验）
│
├── src/main/resources/
│   ├── db.properties      # 数据库连接配置（不提交真实账号密码）
│   └── schema.sql         # 数据库建表脚本
│
├── webapp/
│   ├── index.jsp
│   ├── item/              # 商品列表、详情、发布
│   └── user/              # 登录、注册
│
├── uploads/               # 商品图片上传目录
│
├── .gitignore
├── README.md
└── pom.xml / build.gradle
```

---

## 🗄️ 数据库设计（简表）

### users

| 字段            | 说明     |
| ------------- | ------ |
| id            | 主键     |
| username      | 唯一用户名  |
| password_hash | 哈希后的密码 |
| email         | 邮箱     |
| created_at    | 注册时间   |

### items

| 字段          | 说明                      |
| ----------- | ----------------------- |
| id          | 主键                      |
| seller_id   | 发布者                     |
| title       | 标题                      |
| description | 描述                      |
| price       | 价格                      |
| status      | ACTIVE / SOLD / DELETED |
| created_at  | 时间                      |

### item_images

| 字段      | 说明   |
| ------- | ---- |
| id      | 主键   |
| item_id | 商品ID |
| url     | 图片路径 |

### orders

| 字段         | 说明                  |
| ---------- | ------------------- |
| id         | 主键                  |
| buyer_id   | 买家                  |
| item_id    | 商品                  |
| status     | CREATED / PAID（可扩展） |
| amount     | 成交金额                |
| created_at | 下单时间                |

### favorites

| 字段      | 说明 |
| ------- | -- |
| user_id | 用户 |
| item_id | 商品 |

---


## 📄 License

该项目用于课程作业与学习目的，不建议商业使用。

---

