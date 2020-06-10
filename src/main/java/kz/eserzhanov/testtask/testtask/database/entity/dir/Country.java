package kz.eserzhanov.testtask.testtask.database.entity.dir;

import kz.eserzhanov.testtask.testtask.database.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dir_country")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country extends BaseEntity {
    @Column(name = "code", nullable = false, unique = true)
    private Long code;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cityList;

    @OneToOne
    @JoinColumn(name = "capital_id", referencedColumnName = "id", unique = true)
    private City capital;
}
