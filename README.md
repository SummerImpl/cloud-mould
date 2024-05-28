# cloud-mould
辅助工具的搭建
docker  nacos  安装
docker run --name nacos-quick -e MODE=standalone -p 8848:8848 -p 9848:9848 -p 9849:9849 -d nacos/nacos-server

docker sentinel 安装
docker pull bladex/sentinel-dashboard
docker run --name sentinel \-p 8858:8858 \--privileged=true \--restart=always \-d bladex/sentinel-dashboard