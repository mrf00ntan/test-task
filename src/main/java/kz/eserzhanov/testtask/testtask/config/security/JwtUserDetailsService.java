package kz.eserzhanov.testtask.testtask.config.security;

import kz.eserzhanov.testtask.testtask.config.security.jwt.JwtUserFactory;
import kz.eserzhanov.testtask.testtask.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return JwtUserFactory.create(userService.getByLogin(login));
    }
}
