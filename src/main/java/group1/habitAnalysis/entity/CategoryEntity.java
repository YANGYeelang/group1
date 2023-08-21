package group1.habitAnalysis.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

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
    @Column(nullable = false, length = 500)
    private String description_th;
    @Column(nullable = false, length = 500)
    private String description_en;
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<ChoiceEntity> choiceEntities;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<HistoryEntity> historyEntities;
}
