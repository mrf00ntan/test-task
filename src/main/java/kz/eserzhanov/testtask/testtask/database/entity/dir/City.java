package kz.eserzhanov.testtask.testtask.database.entity.dir;

import kz.eserzhanov.testtask.testtask.database.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dir_city")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City extends BaseEntity {
    @Column(name = "code", nullable = false, unique = true)
    private Long code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false, unique = true)
    private Country country;
}
