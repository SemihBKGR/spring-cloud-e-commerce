# spring cloud e-commerce

A sample Spring Cloud based application

### Modules

- [config-server](./config-server) 
- [discovery-server](./discovery-server)
- [gateway-server](./gateway-server)
- [ui-server](./ui-server)
- [user-server](./user-server)
- [production-server](./production-server)
- [image-server](./image-server)
- [log-server](./log-server)
- [model-common](./model-common)

### Services

Service list with default properties

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
  - port 9090
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
  - port: 2181
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
