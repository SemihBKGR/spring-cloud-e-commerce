package com.semihbkgr.ecommerce.userserver.util;

import com.semihbkgr.ecommerce.modelcommon.user.User;
import com.semihbkgr.ecommerce.modelcommon.user.UserInfo;
import lombok.NonNull;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

    public static User usrOf(@NonNull UserRepresentation userRep) {
        return User.builder()
                .username(userRep.getUsername())
                .firstname(userRep.getFirstName())
                .lastname(userRep.getLastName())
                .email(userRep.getEmail())
                .build();
    }

    public static UserInfo usrInfOf(@NonNull UserRepresentation userRep) {
        return UserInfo.builder()
                .username(userRep.getUsername())
                .firstname(userRep.getFirstName())
                .lastname(userRep.getLastName())
                .build();
    }

    public static List<UserInfo> usrInfLstOf(@NonNull List<? extends UserRepresentation> userRepList) {
        return userRepList.stream()
                .map(UserUtils::usrInfOf)
                .collect(Collectors.toList());
    }

}
