package io.secyruty.springsecuritymaster;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
