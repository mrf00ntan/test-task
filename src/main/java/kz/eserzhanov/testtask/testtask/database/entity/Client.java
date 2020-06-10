package kz.eserzhanov.testtask.testtask.database.entity;

import kz.eserzhanov.testtask.testtask.database.entity.dir.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client extends BaseEntity{
    @Column(name = "iin", nullable = false, unique = true)
    private Long iin;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "birthday", nullable = false)
    private Date birthDay;

    @Column(name = "phone", nullable = false)
    private Long phone;
}
