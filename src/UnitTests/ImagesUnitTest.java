//package UnitTests;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import Model.Filters;
//import Model.Sepia;
//import org.junit.jupiter.api.Test;
//
//import Model.Images;
//
//import java.io.File;
//
///**
// * Unit Test for Images class. Does not test getters/setters, and
// * methods related to history, as we cannot check timestamps to
// * expected values.
// *
// * @author Martin Baroody
// * @author Jai Aggarwal
// * @author Aleksa Zatezalo
// * @author Saksham Ahluwalia
// */
//public class ImagesUnitTest{
//
//    //File to test methods on. Recalled before each method to simulate choosing "new" picture like in GUI.
//
//
//    @Test
//    void testAddTag(){
//        Images img = new Images("Resources/test_image1.png");
//        img.addTag("home");
//        assertEquals("test_image1 @home", img.getFileName());
//        img.removeTag("home");
//    }
//
//    @Test
//    void testRemoveTag(){
//        Images img2 = new Images("Resources/test_image2 @home.png");
//        img2.removeTag("home");
//        assertEquals("test_image2", img2.getFileName());
//        img2.addTag("home");
//    }
////
//    @Test
//    void testUpdate(){
//        Images img3 = new Images("Resources/test_image3.png");
//        img3.update("canada");
//        assertEquals("canada", img3.getFileName());
//        img3.update("test_image3");
//}
//    @Test
//    void testApplyFilter(){
//        Images img4 = new Images("Resources/test_image4.png");
//        img4.applyFilter(new Filters(new Sepia()));
//        File filtered = new File("Resources/test_image4 Sepia.png");
//        boolean check = filtered.exists();
//        assertEquals(true, check);
//        filtered.delete();
//
//    }
//
//}