package kz.eserzhanov.testtask.testtask.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponsePojo {
    private String token;
    private String roleName;
}
