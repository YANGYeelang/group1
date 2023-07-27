package group1.habitAlnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "gmail")
@Data
public class GmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
}
