package com.semihbkgr.ecommerce.modelcommon.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class User {

    private String username;
    private String firstname;
    private String lastname;
    private String email;

}
