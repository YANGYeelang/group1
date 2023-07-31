package group1.habitAlnalysis.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Blob;

@Data
@Entity
@Table(name = "image")
@Builder
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    @Lob
    private byte[] imageData;
//    private String category;
}
