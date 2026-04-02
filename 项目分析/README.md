# capinfo-gjj-busi-grdk 项目分析

## 项目概述
capinfo-gjj-busi-grdk 是一个用于个人住房贷款业务的后端工程，涵盖贷款申请、合同管理、还款、逾期处理等核心业务流程。

## 系统功能
- **贷款申请（sq）**：处理个人贷款申请流程
- **合同管理（ht）**：贷款合同的签订与管理
- **变更管理（bg）**：贷款信息变更操作
- **还款管理（hk）**：支持正常还款与提前还款
- **逾期管理（yq）**：逾期判定、催收与罚息计算
- **楼盘备案（lp）**：房地产项目备案管理
- **审批流程（sp）**：贷款审批流程控制
- **核心服务（core）**：通用逻辑与配置
- **聚合服务（agg）**：对外聚合接口服务（如 gm 子模块）

## 技术架构
- **架构模式**：微服务架构，模块化设计
- **分层结构**：api（接口层）、app（应用层）、busi（业务层）分离
- **技术栈**：Spring Boot、Maven、JDK 17
- **数据库支持**：MySQL、PostgreSQL、达梦、Kingbase（通过 profile 切换）
- **部署方式**：Docker + Kubernetes

## 项目目录结构
```
capinfo-gjj-busi-grdk/
├── capinfo-gjj-busi-grdk-sq      # 贷款申请模块
├── capinfo-gjj-busi-grdk-ht      # 合同管理模块
├── capinfo-gjj-busi-grdk-bg      # 变更管理模块
├── capinfo-gjj-busi-grdk-hk      # 还款管理模块
├── capinfo-gjj-busi-grdk-yq      # 逾期管理模块
├── capinfo-gjj-busi-grdk-lp      # 楼盘备案模块
├── capinfo-gjj-busi-grdk-sp      # 审批流程模块
├── capinfo-gjj-busi-grdk-core    # 核心公共模块
├── capinfo-gjj-busi-grdk-agg     # 聚合服务模块
├── docker                        # Dockerfile 集中存放
├── k8s                           # Kubernetes 部署文件
├── sql                           # 数据库脚本
└── report                        # 报表配置
```

## 构建与部署
### 构建命令
```bash
# 基础构建
mvn clean install -s settings-tsp.xml

# 指定环境构建（例如开发环境 MySQL）
mvn clean install -P dev-mysql -s settings-tsp-dev.xml
```

### 部署命令
```bash
# 构建 Docker 镜像
docker build -f docker/tsp/Dockerfile-capinfo-gjj-busi-grdk-agg-gm-svc -t capinfo/agg-gm-svc:latest .

# K8s 部署
kubectl apply -f k8s/dev-tsp/capinfo-gjj-busi-grdk-agg-gm-svc-dev-tsp.yml -n dev-tsp
```

## 开发环境要求
- JDK 17
- Maven
- Git
- Docker（用于构建镜像）
- Kubernetes（用于部署）

## 依赖管理
- 父工程: `capinfo-gjj-basic-dependencies:1.3-SNAPSHOT`
- 核心 Starter 包括：
  - `capinfo-gjj-feign-basic-stater`
  - `capinfo-gjj-knife4j-expand-starter`（API 文档）
  - `capinfo-gjj-operate-log-expand-starter`（操作日志）
  - `capinfo-gjj-db-basic-starter`（数据库基础）
  - `capinfo-gjj-validate-basic-starter`（参数校验）