package kz.eserzhanov.testtask.testtask.service;

import kz.eserzhanov.testtask.testtask.database.entity.Client;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.dto.ClientDto;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    /**
     * @param clientDto
     * @return save client entity
     */
    Boolean save(ClientDto clientDto, User user);

    /**
     * @param id
     * @return by id
     */
    Client getById(Long id) throws SelfException;

    /**
     * @param client
     * @return delete entity
     */
    Boolean delete(Client client, User user) throws SelfException;

    Page<Client> getWithPageable(int page, User user);

    List<Client> search(String value, User user);
}
