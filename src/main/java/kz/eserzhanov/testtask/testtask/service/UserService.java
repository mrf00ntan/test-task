package kz.eserzhanov.testtask.testtask.service;

import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import org.springframework.security.core.Authentication;

public interface UserService {
    /**
     * @param login
     * @return get by email
     * @throws SelfException
     */
    User getByLogin(String login) throws SelfException;

    User getByAuth(Authentication authentication) throws SelfException;

    User getById(Long id);
}
