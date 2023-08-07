package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
//    @ManyToOne
//    @JoinColumn(name = "email", referencedColumnName = "email")
//    private ResultEntity result;
    private String userName;
}
