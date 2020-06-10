package kz.eserzhanov.testtask.testtask.service.impl;

import kz.eserzhanov.testtask.testtask.database.entity.RequestEntity;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.database.repository.RequestEntityRepository;
import kz.eserzhanov.testtask.testtask.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestEntityRepository requestEntityRepository;

    @Autowired
    public RequestServiceImpl(RequestEntityRepository requestEntityRepository) {
        this.requestEntityRepository = requestEntityRepository;
    }

    @Override
    public Page<RequestEntity> getWithPageable(int page, User user) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id"));
        return requestEntityRepository.findAllByUser(user, pageable);
    }
}
