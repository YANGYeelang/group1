package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length =100)
    private String categoryNameTh;
    @Column(nullable = false, length = 100)
    private String categoryNameEn;
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<UserHistoryEntity> userHistory;
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<ChoiceEntity> choice;
}
