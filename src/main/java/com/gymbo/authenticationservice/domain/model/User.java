package com.gymbo.authenticationservice.domain.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
