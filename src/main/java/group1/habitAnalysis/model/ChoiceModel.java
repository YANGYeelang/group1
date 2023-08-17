package group1.habitAnalysis.model;

import lombok.Data;

@Data
public class ChoiceModel {
    private Integer id;
    private String choiceTh;
    private String choiceEn;
    private Integer categoryId;
}
