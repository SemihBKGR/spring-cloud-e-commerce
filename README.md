# e-commerce

Spring cloud microservice arthitectured web project

### Servers

- config
- discovery
- user

### Build commands

```console
docker-compose --env-file=.env.dev up
```

### Keycloak

Remove keycloak export volume file and import command in docker-compose file when it causes an error and configure
keycloak manually.

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
