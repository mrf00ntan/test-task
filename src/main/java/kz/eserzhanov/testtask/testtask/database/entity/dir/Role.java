package kz.eserzhanov.testtask.testtask.database.entity.dir;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.eserzhanov.testtask.testtask.database.entity.BaseEntity;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dir_roles")
@Data
public class Role extends BaseEntity {
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> userList;
}
