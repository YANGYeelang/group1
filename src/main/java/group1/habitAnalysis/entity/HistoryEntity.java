package group1.habitAnalysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "history")
public class HistoryEntity {
    @Id
    private String HistoryId;
    private LocalDateTime createDate;

    private Integer categoryId;
    private String descriptionTh;
    private String descriptionEn;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    @JsonIgnore
    private UserEntity user;
    @OneToMany(mappedBy = "history", orphanRemoval = true)
    private List<HistoryDetailEntity> historyDetail;
}
