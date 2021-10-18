package com.semihbkgr.ecommerce.ecommerce.userserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private int id;
    private String name;
    private String password;
    private String email;

}
