sudo docker run -dit -p 8085:8080 \
 -e BASE_URL=/api \
 -e SWAGGER_JSON=/usr/share/nginx/html/api/load-injector-api.json \
 -v ~/workspace/opensource/sandstorm/sandstorm-controller/controller-rest/src/main/api-defs:/usr/share/nginx/html/api \
 --name sandstorm-swagger-ui swaggerapi/swagger-ui


docker exec -it sandstorm-swagger-ui sh

docker container stop sandstorm-swagger-ui; docker container prune