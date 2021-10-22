package com.semihbkgr.ecommerce.ecommerce.userserver.service;


import com.semihbkgr.ecommerce.ecommerce.userserver.util.Page;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;


import java.util.List;

public interface UserService {

    UserRepresentation findByUsername(String username);

    List<UserRepresentation> searchByUsername(String username, Page page);

}
