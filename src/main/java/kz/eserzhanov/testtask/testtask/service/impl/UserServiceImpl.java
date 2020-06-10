package kz.eserzhanov.testtask.testtask.service.impl;

import kz.eserzhanov.testtask.testtask.config.security.jwt.JwtUser;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.database.repository.UserRepository;
import kz.eserzhanov.testtask.testtask.exception.MapException;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import kz.eserzhanov.testtask.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final MapException mapException;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(MapException mapException, UserRepository userRepository) {
        this.mapException = mapException;
        this.userRepository = userRepository;
    }

    @Override
    public User getByLogin(String login) throws SelfException {
        Optional<User> optional = userRepository.findByLogin(login);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new SelfException("User not found, by login:" + login, mapException.getException("user", "notFound"));
    }

    @Override
    public User getByAuth(Authentication authentication) throws SelfException {
        JwtUser authUser = (JwtUser) authentication.getPrincipal();
        return getById(authUser.getId());
    }

    @Override
    public User getById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseGet(User::new);
    }
}
