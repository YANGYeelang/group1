package group1.habitAnalysis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AllHistory {
    private Integer choiceId;
    private String descriptionTh;
    private String descriptionEn;
    private Integer categoryId;
    private String userEmail;
}
