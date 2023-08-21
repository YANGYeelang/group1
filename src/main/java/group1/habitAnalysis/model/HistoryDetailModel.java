package group1.habitAnalysis.model;

import lombok.Data;

@Data
public class HistoryDetailModel {
    private Integer choiceId;
    private String choiceTh;
    private String choiceEn;
    private Integer categoryId;
    private boolean type;

}
