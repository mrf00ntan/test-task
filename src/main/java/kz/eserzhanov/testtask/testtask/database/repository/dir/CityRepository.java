package kz.eserzhanov.testtask.testtask.database.repository.dir;

import kz.eserzhanov.testtask.testtask.database.entity.dir.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
