package kz.eserzhanov.testtask.testtask.config.security.jwt;

import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.database.entity.dir.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());

        return new JwtUser(
            user.getId(),
            user.getLogin(),
            user.getPassword(),
            mapToGrantedAuthorities(roles)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRoleName())
                ).collect(Collectors.toList());
    }
}
