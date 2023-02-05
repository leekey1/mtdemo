# 基础工具

## java maven git node

echo "export PATH=/usr/local/gnupg/bin:$PATH" >> ~/.bash_profile

 安装brew 
 ```
 锦鲤

 /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
 
 ```

# 中间件 Tools

## mysql 

brew install mysql

mysql.server start
mac: brew services start mysql

## redis

brew install redis

local start 

redis-server /usr/local/etc/redis.conf

zhg: 
redis-server /Users/anjung-geun/zhg/workspace_java/mtdemo/doc/env/redis.conf
## nginx 

brew install nginx

local start 

sudo nginx 

sudo nginx -s reload -c /usr/local/etc/nginx/nginx.conf
zhg: 
nginx -s reload -c /Users/anjung-geun/zhg/workspace_java/mtdemo/doc/env/nginx.conf



## zookeeper


ab
## rabbitMQ 



