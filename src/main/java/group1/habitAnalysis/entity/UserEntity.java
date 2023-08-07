package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String email;
    private String userName;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<ResultEntity> result;
}
