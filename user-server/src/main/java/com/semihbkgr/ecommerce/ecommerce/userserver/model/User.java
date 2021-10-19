package com.semihbkgr.ecommerce.ecommerce.userserver.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class User {

    @Id
    private int id;
    private String name;
    private String password;
    private String email;

}
