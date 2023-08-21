package group1.habitAnalysis.model;

import group1.habitAnalysis.entity.HistoryDetailEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class HistoryModel {
    private String historyId;
    private LocalDateTime createDate;
    private Integer categoryId;
    private String userEmail;
    private List<DetailModel> historyDetail;
}
