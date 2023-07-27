package group1.habitAlnalysis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imgUrl;
    private String category;
}
