# 工程结构介绍

## 模块依赖关系

                                      --------------
                                      |            |
                                      |   common   |
                                      |            |
                                      --------------
                                     /              \
                                    /                \
                                   /                  \
                                 |/_                  _\|
                      --------------                    --------------
                      |            |                    |            |
                      |   domain   |                    |   client   |
                      |            |                    |            |
                      --------------                    --------------
                     /              \
                    /                \
                   /                  \
                 |/_                  _\|
     --------------                    --------------
     |            |                    |            |
     |    biz     |                    |  resource  |
     |            |                    |            |
     --------------                    --------------
                   \                  /     
                    \                /  
                     \              /   
                     _\|          |/_   
                      -------------- 
                      |            | 
                      |    api     | 
                      |            | 
                      -------------- 

## 各模块职责
- common：公共包，含公共常量&通用定义，服务接口定义
    - 公共常量const、枚举enum、通用util类、异常类
    - RPC服务接口定义Service
    - 输入输出对象：Request、Response、DTO
    - 代码结构如下
        ```
        - com.${company}.${deparment}.${business}.${appname}
        |- common
        | |- consts
        | |- enums
        | |- utils
        | \_ exception
        |- data
        | |- request
        | |- response
        | \_ dto
        \_ service
        ```
- client：实现富客户端
    - 富客户端
    - 代码结构如下
        ```
        - com.${company}.${deparment}.${business}.${appname}
        \_ client
        ```
- domain：领域层
    - 领域模型层：领域对象model、领域服务service、资源库repository、事件event、命令command
    - 查询处理器queryHandler
    - 代码结构如下
        ```
        - com.${company}.${deparment}.${business}.${appname}
        \- domain
          |- handler
          |- model
          |- service
          |- command
          |- event
          \- respositry
        ```
- biz：业务层
    - 实现业务流程、业务处理节点
    - 代码结构如下
        ```
        - com.${company}.${deparment}.${business}.${appname}
        |- flow
        \- process
        ```
- resource：资源层，实现数据访问
    - 含数据访问层dal、数据访问对象dao、数据库配置config、数据对象entity、数据映射mapper、数据对象&领域对象工厂
    - 代码结构如下
        ```
        - com.${company}.${deparment}.${business}.${appname}
        \- resource
          |- dal
          |- dao
          |- config
          |- entity
          |- mapper
          \- factory
        ```
- api：应用层（api），实现业务api
    - 定义api层接口（HTTP协议、RPC协议）
    - 代码结构如下
        ```
        - com.${company}.${deparment}.${business}.${appname}
        |- api
        | \- rpc
        |  \- impl
        \- web
          |- controller
          |- config
          \- filter
        ```