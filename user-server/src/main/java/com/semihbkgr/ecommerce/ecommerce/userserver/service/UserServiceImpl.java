package com.semihbkgr.ecommerce.ecommerce.userserver.service;

import com.semihbkgr.ecommerce.ecommerce.userserver.util.Page;
import com.semihbkgr.ecommerce.ecommerce.userserver.util.UserUtils;
import com.semihbkgr.ecommerce.modelcommon.user.User;
import com.semihbkgr.ecommerce.modelcommon.user.UserInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
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
    public User findByUsername(@NonNull String username) throws IllegalStateException {
        var userRepList = keycloak.realm(realm).users().search(username, true);
        if (userRepList.isEmpty())
            return null;
        else if (userRepList.size() == 1)
            return UserUtils.usrOf(userRepList.get(0));
        else
            throw new IllegalStateException("Found more than one user");
    }

    @org.springframework.lang.NonNull
    @Override
    public List<UserInfo> searchByUsername(@NonNull String username, @NonNull Page page) {
        return UserUtils.usrInfLstOf(keycloak.realm(realm).users().search(username, page.startIndex(), page.pageSize, true));
    }

}
