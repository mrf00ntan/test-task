package kz.eserzhanov.testtask.testtask.database.repository;

import kz.eserzhanov.testtask.testtask.database.entity.RequestEntity;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestEntityRepository extends JpaRepository<RequestEntity, Long> {
    Page<RequestEntity> findAllByUser(User user, Pageable pageable);
}
