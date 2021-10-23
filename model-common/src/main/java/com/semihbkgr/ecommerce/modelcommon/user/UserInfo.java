package com.semihbkgr.ecommerce.modelcommon.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class UserInfo {

    private String username;
    private String firstname;
    private String lastname;

}