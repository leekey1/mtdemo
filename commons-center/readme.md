# commons-center

in local test copy config to config2 
    cd config2
    git init
    git add -A
    git commit -m "local test"

check git lable; latest version is main 

- Spring Cloud构建微服务架构 分布式配置中心

Querying the Configuration
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
    
    http://localhost:10000/app/dev/master
    http://localhost:10000/app/dev/master
    http://localhost:10000/app-dev.yml
    http://localhost:10000/master/app-dev.yml
    http://localhost:10000/master/app-dev.yml

