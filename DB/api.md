# api文档
# 一. 注册模块
## 1.发送注册信息
### 请求url及方式

| 字段       | 属性   |
| :-----:   | :-----:|
| 请求类型   | post   |
| url        |  http://182.254.154.240:8080/wechat/register/create |

### 请求参数

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| email      | String   | 邮箱   |
| username   | String   | 用户名 |
| password   | String   | 密码   |



### 返回参数，json格式

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| status     | String   | 操作成功或失败   |
| msg        | String   | 错误信息 |






## 2.验证邮箱验证码
### 请求url及方式

| 字段       | 属性   |
| :-----:   | :-----:|
| 请求类型   | post   |
| url        |  http://182.254.154.240:8080/wechat/register/code |

### 请求参数

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| email      | String   | 邮箱   |
| code   | String   | 验证码 |



### 返回参数，json格式

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| status     | String   | 操作成功或失败   |
| msg        | String   | 错误信息 |






# 二. 登录模块
## 1.邮箱加密码登录
### 请求url及方式

| 字段       | 属性   |
| :-----:   | :-----:|
| 请求类型   | post   |
| url        |  http://182.254.154.240:8080/wechat/login/pwd |

### 请求参数

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| email      | String   | 邮箱   |
| password   | String   | 密码   |



### 返回参数，json格式

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| status     | String   | 操作成功或失败   |
| msg        | String   | 错误信息 |
| token        | String   | 登录成功就返回token, 用来验证前后端 |

# 三. 修改个人信息模块

## 1.修改用户的头像
### 请求url及方式

| 字段       | 属性   |
| :-----:   | :-----:|
| 请求类型   | post   |
| url        |  http://182.254.154.240:8080/wechat/update/headImg |

### 请求参数

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| email      | String   | 邮箱   |
| file   | file   | 用户头像   |



### 返回参数，json格式

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| status     | String   | 操作成功或失败   |
| msg        | String   | 错误信息 |








## 2.修改用户的个人信息
### 请求url及方式

| 字段       | 属性   |
| :-----:   | :-----:|
| 请求类型   | post   |
| url        |  http://182.254.154.240:8080/wechat/update/user |

### 请求参数

| 字段       | 类型     | 意义       | 是否必选   |
| :-----:    | :-----:  | :-----:    | :-----:    |
| email      | String   | 邮箱       |  是        |
| username   | String   | 昵称       |  否        |
| address    | String   | 地址       |  否        |
| sex        | String   | 性别       |  否        |
| district   | String   | 地区       |  否        |
| sign       | String   | 个性签名   |  否        |

### 返回参数，json格式

| 字段       | 类型     | 意义   |
| :-----:    | :-----:  | :-----:|
| status     | String   | 操作成功或失败   |
| msg        | String   | 错误信息 |



# 三. 好友管理模块

## 1.查找用户
### 请求url及方式

| 字段       | 属性   |
| :-----:    | :-----:|
| 请求类型   | post   |
| url        |  http://182.254.154.240:8080/wechat/friends/search |

### 请求参数

| 字段       | 类型     | 意义   | 是否必选   |
| :-----:    | :-----:  | :-----:| :--------: |
| email      | String   | 邮箱   |	是        |




### 返回参数，json格式

| 字段       | 类型     | 意义             |
| :-----:    | :-----:  | :-----:          |
| status     | String   | 操作成功或失败   |
| username   | String   | 用户昵称         |
| imgUrl     | String   | 用户头像url      |
| msg        | String   | 错误信息         |

## 2.添加好友(估计需要用到socket来实现)
### 请求url及方式

| 字段       | 属性                                            |
| :-----:    | :-----:                                         |
| 请求类型   | post                                            |
| url        |  http://182.254.154.240:8080/wechat/friends/new |

### 请求参数

| 字段         | 类型      | 意义         | 是否必选   |
| :-----:      |  :-----:  | :-----:      | :--------: |
| friendEmail  | String    | 好友的邮箱   |	是         |




### 返回参数，json格式

| 字段       | 类型     | 意义        |
| :-----:    | :-----:  | :-----:     |
| username   | String   | 好友昵称    |
| imgUrl     | String   | 好友头像url |
| remark     | String   | 好友备注    |
