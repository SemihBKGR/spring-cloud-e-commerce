package com.semihbkgr.ecommerce.ecommerce.userserver.service;

import com.semihbkgr.ecommerce.ecommerce.userserver.util.Page;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @Nullable
    @Override
    public UserRepresentation findByUsername(@NonNull String username) throws IllegalStateException {
        var userRepList = keycloak.realm(realm).users().search(username, true);
        if (userRepList.isEmpty())
            return null;
        else if (userRepList.size() == 1)
            return userRepList.get(0);
        else
            throw new IllegalStateException("Found more than one user");
    }

    @Override
    public List<UserRepresentation> searchByUsername(@NonNull String username, @NonNull Page page) {
        return keycloak.realm(realm).users().search(username, page.startIndex(), page.pageSize,true);
    }

}
