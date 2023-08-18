package group1.habitAnalysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Data
@Table(name = "choice")
public class ChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String choiceTh;
    @Column(nullable = false)
    private String choiceEn;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private CategoryEntity category;

    @OneToMany(mappedBy = "choice", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<HistoryDetailEntity> historyDetail;

}
