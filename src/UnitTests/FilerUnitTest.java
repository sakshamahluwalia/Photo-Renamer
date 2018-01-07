//package UnitTests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import Model.Filer;
//import Model.TagManager;
//import Model.Sepia;
//import org.junit.jupiter.api.Test;
//import Model.Images;
//
//import java.awt.*;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// * Unit Test for Filer class. Does not test getters/setters.
// *
// * @author Martin Baroody
// * @author Jai Aggarwal
// * @author Aleksa Zatezalo
// * @author Saksham Ahluwalia
// */
//
//public class FilerUnitTest {
//
//    private Filer filer = new Filer("Resources");
//
//    @Test
//    void testGetImageList(){
//    ArrayList<Images> imageList = filer.getImageList();
//    ArrayList<String> fileList = new ArrayList<>();
//    ArrayList<String> imageNameList = new ArrayList<>();
//    String[] fileLst = new String[]{"test_image1", "test_image2 @home", "test_image3", "test_image4"};
//    fileList.addAll(Arrays.asList(fileLst));
//    for (Images image: imageList){
//        imageNameList.add(image.getFileName());
//    }
//    assertEquals(fileList, imageNameList);
//    }
//
//    @Test
//    void testGetAllFilesinDir(){
//        ArrayList<Images> imageList = filer.getAllFilesInDir();
//        ArrayList<String> fileList = new ArrayList<>();
//        ArrayList<String> imageNameList = new ArrayList<>();
//        String[] fileLst = new String[]{"test_image1", "test_image2 @home", "test_image3", "test_image4", "placeholder", "sub_1"};
//        fileList.addAll(Arrays.asList(fileLst));
//        for (Images image: imageList){
//            imageNameList.add(image.getFileName());
//        }
//        assertEquals(fileList, imageNameList);
//    }
//
//    @Test
//    void testGetFileNames(){
//        ArrayList<Images> imageList = filer.getAllFilesInDir();
//        ArrayList<String> fileList = new ArrayList<>();
//        ArrayList<String> imageNameList = filer.getFileNames(imageList);
//        String[] fileLst = new String[]{"test_image1", "test_image2 @home", "test_image3", "test_image4", "placeholder", "sub_1"};
//        fileList.addAll(Arrays.asList(fileLst));
//        assertEquals(fileList, imageNameList);
//    }
//
//    @Test
//    void testGetImageAtIndex(){
//        Images imageAtIndex = filer.getImageAtIndex(2);
//        assertEquals("test_image3", imageAtIndex.getFileName());
//    }
//
//    @Test
//    void testMoveFile(){
//        File origin = new File("Resources/origin/sub_1.png");
//        File destination = new File("Resources/destination/sub_1.png");
//        filer.moveFile(origin.getAbsolutePath(), destination.getAbsolutePath());
//        File file = new File("Resources/destination/sub_1.png");
//        boolean checker = true;
//        if (!file.exists()) {
//            checker = false;
//        }
//        assertEquals(true, checker);
//        filer.moveFile(destination.getAbsolutePath(), origin.getAbsolutePath());
//
//    }
//}
