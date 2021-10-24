package com.semihbkgr.ecommerce.productionserver.api;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import com.semihbkgr.ecommerce.modelcommon.production.ProductionInfo;
import com.semihbkgr.ecommerce.productionserver.component.IdGenerator;
import com.semihbkgr.ecommerce.productionserver.repository.ProductionRepository;
import com.semihbkgr.ecommerce.productionserver.service.ProductionServiceImpl;
import com.semihbkgr.ecommerce.productionserver.test.config.KeycloakSecurityTestConfig;
import com.semihbkgr.ecommerce.productionserver.test.util.ProductionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.adapters.springboot.KeycloakAutoConfiguration;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductionApi.class)
@Import({ProductionServiceImpl.class, KeycloakSecurityTestConfig.class})
@EnableAutoConfiguration(exclude = KeycloakAutoConfiguration.class)
@ActiveProfiles("test")
class ProductionApiTest {

    @MockBean
    ProductionRepository productionRepository;

    @MockBean
    IdGenerator idGenerator;

    @Autowired
    WebTestClient webClient;

    @Test
    @DisplayName("Save")
    void save() {
        final var productionId = "test-production-id";
        final var production = ProductionUtils.instance();
        Mockito.when(idGenerator.generate())
                .thenReturn(productionId);
        Mockito.when(productionRepository.save(production.withId(productionId)))
                .thenReturn(Mono.just(production.withId(productionId)));
        webClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(production))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Production.class)
                .isEqualTo(production.withId(productionId));
        Mockito.verify(idGenerator, Mockito.times(1))
                .generate();
        Mockito.verify(productionRepository, Mockito.times(1))
                .save(production.withId(productionId));
    }

    @Test
    @DisplayName("Update")
    void update() {
        final var productionId = "test-production-id";
        final var production = ProductionUtils.instance();
        Mockito.when(productionRepository.save(production.withId(productionId)))
                .thenReturn(Mono.just(production.withId(productionId)));
        Mockito.when(productionRepository.findById(productionId))
                .thenReturn(Mono.just(production.withId(productionId)));
        webClient.put()
                .uri("/" + productionId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(production))
                .exchange()
                .expectStatus()
                .isAccepted()
                .expectBody(Production.class)
                .isEqualTo(production.withId(productionId));
        Mockito.verify(productionRepository, Mockito.times(1))
                .save(production.withId(productionId));
        Mockito.verify(productionRepository, Mockito.times(1))
                .findById(productionId);
    }

    @Test
    @DisplayName("FindById")
    void findById() {
        String productionId = "test-production-id";
        final var production = ProductionUtils.instance(productionId);
        Mockito.when(productionRepository.findById("test-production-id"))
                .thenReturn(Mono.just(production));
        webClient.get()
                .uri("/" + "test-production-id")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Production.class)
                .isEqualTo(production);
        Mockito.verify(productionRepository, Mockito.times(1))
                .findById(productionId);
    }

    @Test
    @DisplayName("FindAll")
    void findAll() {
        final var productionInfo01 = ProductionUtils.infoInstance("test-production-id-01");
        final var productionInfo02 = ProductionUtils.infoInstance("test-production-id-02");
        final var productionInfo03 = ProductionUtils.infoInstance("test-production-id-03");
        Mockito.when(productionRepository.findAllBy(ArgumentMatchers.any()))
                .thenReturn(Flux.just(productionInfo01, productionInfo02, productionInfo03));
        webClient.get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ProductionInfo[].class)
                .isEqualTo(new ProductionInfo[]{productionInfo01, productionInfo02, productionInfo03});
        Mockito.verify(productionRepository, Mockito.times(1))
                .findAllBy(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("DeleteById")
    void deleteById() {
        final var productionId = "test-production-id";
        final var production = ProductionUtils.instance(productionId);
        Mockito.when(productionRepository.findById(productionId))
                .thenReturn(Mono.just(production));
        Mockito.when(productionRepository.deleteById(productionId))
                .thenReturn(Mono.empty());
        webClient.delete()
                .uri("/" + productionId)
                .exchange()
                .expectStatus()
                .isAccepted()
                .expectBody(Production.class)
                .isEqualTo(production);
        Mockito.verify(productionRepository, Mockito.times(1))
                .findById("test-production-id");
        Mockito.verify(productionRepository, Mockito.times(1))
                .deleteById(productionId);
    }

}