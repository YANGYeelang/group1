package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "result")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private int cow;
    private int rat;
    private int bear;
    private int eagle;
}
