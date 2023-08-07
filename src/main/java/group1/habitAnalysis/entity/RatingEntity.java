package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rating")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
}
