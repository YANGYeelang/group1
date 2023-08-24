package group1.habitAnalysis.model;

import group1.habitAnalysis.entity.ChoiceEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ArrayList<Integer> choiceIdLike;
    private ArrayList<Integer> choiceIdDisLike;
    private String userEmail;
}
