# 文件上传下载

用于上传文件和下载文件

## api接口

### 文件下载

> GET：/down/{filename:.+}

####  Path参数

| 属性     | 说明         |
| -------- | ------------ |
| filename | 自定义文件名 |

#### RequestParam

| 属性  | 类型   | 默认值       | 必填 | 说明               |
| ----- | ------ | ------------ | ---- | ------------------ |
| id    | Long   |              | 是   | 根据ID查找文件     |
| style | String | null（下载） | 否   | 判断下载或在线浏览 |

### 文件上传

> POST:/upload
>
> type:Multipart

#### RequestParam

| 属性 | 类型          | 默认值 | 必填 | 说明           |
| ---- | ------------- | ------ | ---- | -------------- |
| file | MultipartFile |        | 是   | 你要上传的文件 |

#### object.success 回调函数

> type:json

| 属性       | 类型   | 说明     |
| ---------- | ------ | -------- |
| id         | Long   | 文件ID   |
| size       | Long   | 文件大小 |
| type       | String | 文件类型 |
| uploadTime | Long   | 上传时间 |
| fileName   | String | 文件名   |

### 查看文件信息

> GET:/file_info

#### RequestParam

| 属性  | 类型   | 默认值       | 必填 | 说明               |
| ----- | ------ | ------------ | ---- | ------------------ |
| id    | Long   |              | 是   | 根据ID查找文件     |

#### object.success 回调函数

> type:json

| 属性       | 类型   | 说明     |
| ---------- | ------ | -------- |
| id         | Long   | 文件ID   |
| size       | Long   | 文件大小 |
| type       | String | 文件类型 |
| uploadTime | Long   | 上传时间 |
| fileName   | String | 文件名   |