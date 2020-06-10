package kz.eserzhanov.testtask.testtask.service.impl;

import kz.eserzhanov.testtask.testtask.database.entity.Client;
import kz.eserzhanov.testtask.testtask.database.entity.RequestEntity;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.database.repository.ClientRepository;
import kz.eserzhanov.testtask.testtask.database.repository.RequestEntityRepository;
import kz.eserzhanov.testtask.testtask.dto.ClientDto;
import kz.eserzhanov.testtask.testtask.exception.MapException;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import kz.eserzhanov.testtask.testtask.mapper.ClientMapper;
import kz.eserzhanov.testtask.testtask.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final RequestEntityRepository requestEntityRepository;
    private final MapException mapException;

    @Autowired
    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository, RequestEntityRepository requestEntityRepository, MapException mapException) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.requestEntityRepository = requestEntityRepository;
        this.mapException = mapException;
    }

    @Transactional
    @Override
    public Boolean save(ClientDto clientDto, User user) {
        Client client = clientMapper.toClient(clientDto);
        client.setCreator(user);


        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setUser(user);
        String requestParam = String.format("client adding, with IIN: %s", clientDto.getIin());
        requestEntity.setRequestParam(requestParam);
        requestEntityRepository.save(requestEntity);

        return clientRepository.save(client).getId() != null;
    }

    @Override
    public Client getById(Long id) throws SelfException {
        Optional<Client> optional = clientRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new SelfException("Client not found, by id:" + id, mapException.getException("client", "notFound"));
    }

    @Transactional
    @Override
    public Boolean delete(Client client, User user) throws SelfException {
        if(client.getCreator().equals(user)){
            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setUser(user);
            String requestParam = String.format("client deleting, with IIN and id: %s, %d", client.getIin(), client.getId());
            requestEntity.setRequestParam(requestParam);
            requestEntityRepository.save(requestEntity);

            clientRepository.delete(client);
            return true;
        }
        throw new SelfException("Permission denied", mapException.getException("default", "permissionDenied"));
    }

    @Transactional
    @Override
    public Page<Client> getWithPageable(int page, User user) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id"));

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setUser(user);
        String requestParam = String.format("clients pageable showing, with page and size: %s, %s", page, 10);
        requestEntity.setRequestParam(requestParam);
        requestEntityRepository.save(requestEntity);

        return clientRepository.findAllByCreator(user, pageable);
    }

    @Override
    public List<Client> search(String value, User user) {
        return clientRepository.searchByValue(value, user);
    }
}
