package group1.habitAnalysis.model;

import lombok.Data;

@Data
public class HistoryDetailModel {
    private Integer choiceId;
    private String choiceTh;
    private String choiceEn;
    private String description_th;
    private String description_en;
    private Integer categoryId;
    private boolean type;

}
