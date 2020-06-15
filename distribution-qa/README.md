# 题目分配中心

用于统一分配题目

## 子服务API约定接口

| 需求              | 接口           | 类型      |
| ----------------- | -------------- | --------- |
| 获取题目          | /q/{id}        | GET/JSON  |
| 修改题目          | /q/{id}        | PUT/JSON  |
| 删除题目          | /q/{id}        | DELE/JSON |
| 添加题目          | /q             | POST/JSON |
| 添加答案          | /answer        | POST/JSON |
| 修改答案          | /answer/{id}   | PUT/JSON  |
| 删除答案          | /answer/{id}   | DELE/JSON |
| 查看答案          | /answer/{id}   | GET/JSON  |
| 用户添加/修改答案 | /u_answer/{id} | PUT/JSON  |
| 删除用户答案      | /u_answer/{id} | DELE/JSON |
| 查看用户答案      | /u_answer/{id} | GET/JSON  |



## API接口

### 接口总览

| 需求              | 接口           | 类型      | status         |
| ----------------- | -------------- | --------- | -------------- |
| 获取题目          | /qa/{id}       | GET/JSON  | 0              |
| 修改题目          | /qa/{id}       | POST/JSON | 0              |
| 删除/启用题目     | /qa/{id}       | POST/JSON | 删除-1；启动-0 |
| 添加题目          | /qa            | POST/JSON | 0              |
| 添加答案          | /answer        | POST/JSON | 0              |
| 修改答案          | /answer/{id}   | POST/JSON | 0              |
| 删除/启用答案     | /answer/{id}   | POST/JSON | 删除-1；启动-0 |
| 查看答案          | /answer/{id}   | GET/JSON  | 0              |
| 用户添加/修改答案 | /u_answer/{id} | POST/JSON | 0              |
| 删除/启动用户答案 | /u_answer/{id} | POST/JSON | 删除-1；启动-0 |
| 查看用户答案      | /u_answer/{id} | GET/JSON  | 0              |

### 获取题目

> GET:/qa/{id}

####  Path参数

| 属性 | 说明   |
| ---- | ------ |
| id   | 试卷ID |

#### object.data回调函数

> type:json

| 属性         | 类型   | 说明     |
| ------------ | ------ | -------- |
| processParam | object | 试卷配置 |
| subject      | Array  | 题目集合 |

##### object.data.processParam

| 属性 | 类型 | 说明 |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |

##### object.data.subject

由题目服务本身产生

### 添加题目

> POST:/qa
>
> type:json

#### Request Param JSON

| 属性         | 类型   | 默认值 | 必填 | 说明     |
| ------------ | ------ | ------ | ---- | -------- |
| processParam | object |        | 是   | 试卷配置 |
| subject      | Array  |        | 是   | 题目集合 |

##### processParam

| 属性 | 类型 | 默认值 | 必填 | 说明 |
| ---- | ---- | ------ | ---- | ---- |
|      |      |        |      |      |

##### subject

| 属性 | 类型   | 默认值 | 必填 | 说明                   |
| ---- | ------ | ------ | ---- | ---------------------- |
| type | String |        | 是   | 通过type查找对应的服务 |

### 修改题目

> POST:/qa/{id}
>
> type:json

####  Path参数

| 属性 | 说明   |
| ---- | ------ |
| id   | 试卷ID |

#### Request ParamJ SON

| 属性         | 类型   | 默认值 | 必填 | 说明     |
| ------------ | ------ | ------ | ---- | -------- |
| processParam | object |        | 是   | 试卷配置 |
| subject      | Array  |        | 是   | 题目集合 |

##### processParam

| 属性 | 类型 | 默认值 | 必填 | 说明 |
| ---- | ---- | ------ | ---- | ---- |
|      |      |        |      |      |

##### subject

| 属性 | 类型   | 默认值 | 必填 | 说明                   |
| ---- | ------ | ------ | ---- | ---------------------- |
| type | String |        | 是   | 通过type查找对应的服务 |
| id   | object |        | 是   | 要修改的题目ID         |

### 删除服务

> POST:/qa/{id}
>
> type:json

####  Path参数

| 属性 | 说明   |
| ---- | ------ |
| id   | 试卷ID |

#### Request Param JSON

| 属性         | 类型   | 默认值 | 必填 | 说明     |
| ------------ | ------ | ------ | ---- | -------- |
| processParam | object |        | 是   | 试卷配置 |
| subject      | Array  |        | 是   | 题目集合 |

##### processParam

| 属性   | 类型 | 默认值 | 必填 | 说明             |
| ------ | ---- | ------ | ---- | ---------------- |
| status | int  | 0      | 是   | 1为删除，0为使用 |