package group1.habitAnalysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "history_detail")
public class HistoryDetailEntity {
    @Id
    private String historyDetailId;
    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    @JsonIgnore
    private HistoryEntity history;

//    private Integer choiceId;
    private boolean type;

    @ManyToOne
    @JoinColumn(name = "choice_id", nullable = false)
    @JsonIgnore
    private ChoiceEntity choice;
}
