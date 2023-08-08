package group1.habitAnalysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "result")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float cow;
    private float rat;
    private float bear;
    private float eagle;
    @ManyToOne
    @JoinColumn(name = "user_email",nullable = false)
    @JsonIgnore
    private UserEntity user;
}
