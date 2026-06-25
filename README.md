# 个人健康数据管理系统

基于微信小程序 + Spring Boot + Vue 管理后台的个人健康数据管理系统。

## 技术栈

- 后端：Java Spring Boot（`ssmxynp9`）
- 管理后台：Vue + Element UI
- 小程序：微信小程序（uni-app 编译产物）
- 数据库：MySQL

## 快速开始

1. 导入数据库：执行 `weixin125_jiankangshuju/db.sql`
2. 配置数据库：复制 `config.properties.example` 为 `config.properties` 并填写密码
3. 启动后端：在 `weixin125_jiankangshuju/代码/ssmxynp9` 运行 `mvn exec:java`
4. 管理后台：http://localhost:8080/ssmxynp9/admin/dist/index.html （账号 `abo/abo`）
5. 小程序：用微信开发者工具打开 `weixin125_jiankangshuju/代码/mp-weixin`

## 默认账号

| 端 | 账号 | 密码 |
|---|---|---|
| 管理后台 | abo | abo |
| 小程序用户 | 用户1 | 123456 |

## 目录说明

```
weixin125_jiankangshuju/
├── db.sql                 # 数据库脚本
└── 代码/
    ├── ssmxynp9/          # Java 后端 + Admin + Front 源码
    ├── mp-weixin/         # 微信小程序
    └── 启动项目.bat        # 一键启动脚本
```

## 注意事项

- `config.properties` 含数据库密码，已在 `.gitignore` 中排除，请勿提交真实密码
- 小程序 API 地址默认 `localhost:8080`，真机调试需改为实际服务器地址
