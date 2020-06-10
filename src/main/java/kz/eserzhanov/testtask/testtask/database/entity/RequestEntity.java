package kz.eserzhanov.testtask.testtask.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "request_param", nullable = false)
    private String requestParam;

    @PrePersist
    public void setPersist() {
        createdDate = new Date();
    }
}
