package com.semihbkgr.ecommerce.ecommerce.userserver.util;

import com.semihbkgr.ecommerce.ecommerce.userserver.model.User;
import com.semihbkgr.ecommerce.ecommerce.userserver.model.dto.UserCreateDto;
import lombok.NonNull;

public class ModelUtils {

    public static User userOf(@NonNull UserCreateDto userCreateDto){
        return User.builder()
                .id(0)
                .name(userCreateDto.getName())
                .password(userCreateDto.getPassword())
                .build();
    }

}
