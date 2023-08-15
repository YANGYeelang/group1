package group1.habitAnalysis.model;

import lombok.*;

import java.time.LocalDateTime;

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
}
