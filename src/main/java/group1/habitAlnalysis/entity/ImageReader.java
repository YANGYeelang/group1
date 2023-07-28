//package group1.habitAlnalysis.entity;
//
//import group1.habitAlnalysis.entity.ImageEntity;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class ImageReader {
//
//    public static byte[] readImageAsByteArray(String imagePath) throws IOException {
//        Path path = Paths.get(imagePath);
//        return Files.readAllBytes(path);
//    }
//
//    public static void main(String[] args) {
//        try {
//            String imagePath = "C:\\Users\\Yilang\\Desktop\\B.jpg";
//            byte[] imageData = readImageAsByteArray(imagePath);
//
//            // Now you can use the imageData byte array to store or process the image data.
//            // For example, you can set it in your entity object.
//            ImageEntity entity = new ImageEntity();
//            entity.setId(1000);
//            entity.setImg(imageData);
//
//            // Your code to save the entity to the database or do any other processing.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
