package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "choice")
public class ChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String animal;
}
