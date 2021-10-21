package com.semihbkgr.ecommerce.ecommerce.userserver.service;


import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {

    List<UserRepresentation> searchUserByUsername(String username);

}
