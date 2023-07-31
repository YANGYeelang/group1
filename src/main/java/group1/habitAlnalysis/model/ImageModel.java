package group1.habitAlnalysis.model;

import lombok.Data;

@Data
public class ImageModel {
    private Integer id;
    private String name;
    private String type;
    private byte[] imageData;
//    private String category;
}
