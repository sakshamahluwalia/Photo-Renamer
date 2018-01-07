package Controller;

import Model.Logger;
import View.*;

import Model.Filer;
import Model.ImageManager;
import Model.Images;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.*;

public class ImageManagerController{
    private ImageManagerGUI imageView; //the imageManagerGUI to be controlled
    private File directory; //the directory the images are in
    private Stage mainStage; //the stage in which the GUI is set
    private Filer filer;
    private File selectedImg; //keeps track of the image that has been selected
    private boolean subdirectories; //is true if the images in subdirectories should be displayed, else false


    /** Creates a new ImageManagerController
     *
     * @param s: the stage in which this will be set
     * @param view: the ImageManagerGUI to be controlled
     * @param dir: the directory we are working in
     *
     * */
    public ImageManagerController(Stage s, ImageManagerGUI view, File dir) {
        mainStage = s;
        imageView = view;
        directory = dir;
        filer = new Filer(dir.getAbsolutePath());
        subdirectories = false; //at first the user won't see the images in the subdirectories
    }



    /** Gets a list of the image files in the directory
     *
     * @return the list of files
     * */
    public ArrayList<String> getImageFiles() {
        return filer.getFileNames(filer.getImageList());
    }


    /** Displays the selected image on the screen */
    public void listFunc() {
        int index = imageView.getList().getSelectionModel().getSelectedIndex();
        Images i = filer.getImageAtIndex(index);
        try {
            imageView.setIv(i.getFile()); //shows the image on the screen
            selectedImg = i.getFile(); //sets selectedImg to the selection
        }
        catch(MalformedURLException|NullPointerException exc) {
            Logger.writeToFile(exc.getStackTrace());
        }
    }


    /** Switches the scene to an ImageTagGUI*/
    public void updateTagFunc() {
        try {
            //the ImageTagGUI will take in the directory and selected image as arguments
            Scene scene = new Scene(new ImageTagGUI(mainStage, directory, selectedImg), 600, 465);
            mainStage.setScene(scene);
        }
        catch (NullPointerException exc) { //if no image has been clicked we can get a NullPointer
            Logger.writeToFile(exc.getStackTrace());
        }
    }

    /** We switch the scene to HomePageGUI*/
    public void backButtonFunc() {
        Scene s = new Scene(new HomePageGUI(mainStage), 500, 275);
        mainStage.setScene(s);
    }

    /** We set the scene to TagListGUI*/
    public void updateListFunc() {
        //Takes in the directory as an argument
        Scene s = new Scene(new TagListGUI(mainStage, directory), 500, 275);
        mainStage.setScene(s);
    }

    /** Toggles whether to view the subdirectory files or not*/
    public void toggleFunc() {
        if (!subdirectories) { //if the subdirectories aren't current visible
            ArrayList<String> fileNames = filer.getFileNames(filer.getAllFilesInDir());
            ArrayList<Images> imageFiles = filer.getAllFilesInDir();
            for (int i = 0; i < fileNames.size(); i++) {
                fileNames.set(i,  fileNames.get(i) + " (Path: " + imageFiles.get(i).getFile().getAbsolutePath() + ")");
            }
            imageView.setListView(fileNames); //then we view the subdirectory files
            subdirectories = true; //the subdirectory files are now visible so we set this to true
        }
        else { //if the subdirectories are currently visible
            //getImageList() does not contain the subdirectory files
            //so setting the listview to only contain getImageList() will remove the subdirectory files
            imageView.setListView(filer.getFileNames(filer.getImageList()));
            subdirectories = false; //now the subdir files are not visible so we set this to false
        }
    }

}
