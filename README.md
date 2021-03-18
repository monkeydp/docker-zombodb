# SpringBoot Zombodb 示例

SpringBoot + Jpa + Zombodb

前置步骤参考 [Elastic stack (ELK) + Zombodb on Docker 中文版](https://github.com/monkeydp/docker-elkz)

## 教程

### 1. 修改 Elasticsearch 地址

打开 `src/main/resources/sql/V20210318144112__create_product_and_insert_data.sql`

找到 `WITH (url='http://elastic:changeme@192.168.x.x:9200/')`

把 `192.168.x.x` 改成你的主机 ip

### 2. 测试项目

启动 `SpringbootZombodbApplication`

访问 `http://localhost:8080/doc.html`

左侧栏 → `产品` → `产品列表` → `调试`

![](.README/test_zombodb.png)

