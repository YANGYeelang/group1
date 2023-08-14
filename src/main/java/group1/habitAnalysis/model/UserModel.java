package group1.habitAnalysis.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserModel {
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private LocalDateTime createDate;
}
