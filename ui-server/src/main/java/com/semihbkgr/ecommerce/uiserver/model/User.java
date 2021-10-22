package com.semihbkgr.ecommerce.uiserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String firstname;
    private String lastname;
    private String email;

}
