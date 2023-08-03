package group1.habitAnalysis.util;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    public static  byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()){
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        try{
            outputStream.close();
        }catch(Exception ignored){

        }
        return outputStream.toByteArray();
    }


    public static List<byte[]> decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        byte[] buffer = new byte[4*1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        List<byte[]> images  = new ArrayList<>();
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            inflater.end();
            images.add(outputStream.toByteArray());
            return images;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static byte[] decompressImage(byte[] data){
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] tmp = new byte[4*1024];
//        try {
//            while (!inflater.finished()){
//                int count = inflater.inflate(tmp);
//                outputStream.write(tmp, 0, count);
//            }
//            outputStream.close();
//        }catch (Exception ignored){
//
//        }
//        return outputStream.toByteArray();
//    }
}
