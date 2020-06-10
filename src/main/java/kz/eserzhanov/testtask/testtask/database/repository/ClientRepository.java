package kz.eserzhanov.testtask.testtask.database.repository;

import kz.eserzhanov.testtask.testtask.database.entity.Client;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAllByCreator(User user, Pageable pageable);

    @Query("SELECT u FROM Client u INNER JOIN u.city c WHERE " +
            "CONCAT(u.id, '') LIKE %:search% AND u.creator = :creator " +
            "OR CONCAT(u.iin, '') LIKE %:search% AND u.creator = :creator " +
            "OR c.name LIKE %:search% AND u.creator = :creator " +
            "OR u.fullName LIKE %:search% AND u.creator = :creator " +
            "OR CONCAT(u.birthDay, '') LIKE %:search% AND u.creator = :creator " +
            "OR CONCAT(u.phone, '') LIKE %:search% AND u.creator = :creator")
    List<Client> searchByValue(@Param("search") String search, @Param("creator") User creator);
}
