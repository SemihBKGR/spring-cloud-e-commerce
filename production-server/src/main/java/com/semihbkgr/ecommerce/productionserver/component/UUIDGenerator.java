package com.semihbkgr.ecommerce.productionserver.component;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements IdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

}
