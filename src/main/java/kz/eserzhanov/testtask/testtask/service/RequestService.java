package kz.eserzhanov.testtask.testtask.service;

import kz.eserzhanov.testtask.testtask.database.entity.RequestEntity;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import org.springframework.data.domain.Page;

public interface RequestService {
    Page<RequestEntity> getWithPageable(int page, User user);
}
