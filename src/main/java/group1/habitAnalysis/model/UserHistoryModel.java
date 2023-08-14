package group1.habitAnalysis.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserHistoryModel {
    private Long id;
    private String descriptionTh;
    private String descriptionEn;
    private LocalDateTime createDate;
    private Integer categoryId;
    private String userEmail;
}
