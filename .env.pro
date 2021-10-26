# config-server
CONFIG_PORT=8888
CONFIG_HOSTNAME=config
CONFIG_SPRING_PROFILES=native,pro

# discovery-server
DISCOVERY_PORT=8761
DISCOVERY_HOSTNAME=discovery
DISCOVERY_SPRING_PROFILES=pro

# gateway-server
GATEWAY_PORT=9000
GATEWAY_HOSTNAME=user
GATEWAY_SPRING_PROFILES=pro

# ui-server
UI_PORT=8090
UI_HOSTNAME=ui
UI_SPRING_PROFILES=pro

# user-server
USER_PORT=9010
USER_HOSTNAME=user
USER_SPRING_PROFILES=pro

# production-server
PRODUCTION_PORT=9020
PRODUCTION_HOSTNAME=production
PRODUCTION_SPRING_PROFILES=pro

# image-server
IMAGE_PORT=9030
IMAGE_HOSTNAME=image
IMAGE_SPRING_PROFILES=pro

# log-server
LOG_SPRING_PROFILES=pro

# mongodb
MONOGDB_PORT=27017
MONOGDB_HOSTNAME=mongodb
MONGO_INITDB_ROOT_USERNAME=root
MONGO_INITDB_ROOT_PASSWORD=password

# keycloak
KEYCLOAK_PORT=8080
KEYCLOAK_HOSTNAME=keycloak
KEYCLOAK_USER=user
KEYCLOAK_PASSWORD=password

# postgres
POSTGRES_PORT=5432
POSTGRES_HOSTNAME=postgres
POSTGRES_DB=keycloak
POSTGRES_USER=user
POSTGRES_PASSWORD=password
POSTGRES_VENDOR=postgres

# kafka
KAFKA_PORT=29092
KAFKA_HOSTNAME=kafka
KAFKA_BROKER_ID: 1
KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

# zookeeper
ZOOKEEPER_HOSTNAME=zookeeper
ZOOKEEPER_CLIENT_PORT=2181
ZOOKEEPER_TICK_TIME=2000