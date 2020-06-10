package kz.eserzhanov.testtask.testtask.database.entity;

import kz.eserzhanov.testtask.testtask.database.entity.dir.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BaseEntity{
    @OneToMany(mappedBy = "user")
    private List<RequestEntity> request;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Client> clientList;
}
