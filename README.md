# e-commerce

A sample Spring Cloud based application

### Modules

- [config-server](./config-server/README.md) 
- [discovery-server](./discovery-server/README.md)
- [gateway-server](./gateway-server/README.md)
- [ui-server](./ui-server/README.md)
- [user-server](./user-server/README.md)
- [production-server](./production-server/README.md)
- [image-server](./image-server/README.md)
- [log-server](./log-server/README.md)
- [model-common](./model-common/README.md)

### Services

- Config Server
  - port: 8888
  - hostname: config  
- Discovery Server
  - port: 8761
  - hostname: discovery
- Gateway Server
  - port: 9000
  - hostname: gateway
- User Server
  - port: 9010
  - hostname: user  
- Production Server
  - port: 9020
  - hostname: production
- Image Server
  - port: 9030
  - hostname: image
- Log Server  
  - hostname: log
- MongoDB
  - port 27017
  - hostname: mongodb  
- Postgrest
  - port: 5432
  - hostname: postgres  
- Keycloak
  - port: 8080
  - hostname: keycloak
- Zookeeper
  -port: 2181
  - hostname: zookeeper
- Kafka
  - port: 29092
  - hostname: kafka
  
### Build commands

development
```console
docker-compose --env-file=.env.dev up
```

production
```console
docker-compose --env-file=.env.dev up
```

### Keycloak

Remove keycloak export volume file and import command in docker-compose file when it causes an error and configure
keycloak manually.

#### Default keycloak properties

Keycloak admin console

- Username: user 
- Password: password

Keycloak realm

- Name: ecom

Keycloak client in realm
- Client: common-client

Keycloak user in realm
- Username: admin  
- Password: pass
