package Controller;

import Model.*;
import View.*;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Path;

public class ImageTagController{

    private ImageTagGUI view; //the view for this controller
    private Stage stage; //the stage of the application
    private File directory; //the working directory
    private File imgFile; //the file of the image
    private Images image; //the image object which represents the imgFile
    private TagManager tm;
    private Filer filer;

    /** Creates an ImageTagController
     *
     * @param s: the stage on which the application is set
     * @param view_: the view
     * @param dir: working directory
     * @param file: the image file we are working with
     * */
    public ImageTagController(Stage s, ImageTagGUI view_, File dir, File file) {
        stage = s;
        view = view_;
        directory = dir;
        imgFile = file;
        image = new Images(imgFile.getAbsolutePath()); //image now represents imgFile
        tm = new TagManager();
        filer = new Filer(directory.getAbsolutePath());
        setViewText(); //displays the image's tags
    }



    /** Returns the list of tags in this image
     * @return  list of tags in this image
     * */
    private ArrayList<String> getImageTags() {
        return image.getCurrentTags();
    }

    /** Returns the list of all created tags
     * @return list of all created tags
     * */
    public ArrayList<String> getAllTags() {
        return tm.getTagList();
    }

    /** Sets the display text in the GUI to the current list of image tags*/
    public void setViewText() {
        ArrayList<String> imgTags = getImageTags();
        String textStr = "Current tags: ";
        for(String tag : imgTags) {
            textStr = textStr.concat(tag) + ", ";
        }
        view.setTagsText(textStr);
    }


    /** Returns the file object of this image
     * @return this file
     * */
    public File getImgFile() {
        return imgFile;
    }


    /** Adds the clicked tag to the image tags*/
    public void listFunc() {
        try {
            int index = view.getList().getSelectionModel().getSelectedIndex();
            String tag = getAllTags().get(index);
            image.addTag(tag); //add the selected tag to the image
            setViewText(); //update the display text
        }
        catch(ArrayIndexOutOfBoundsException exc) {
            Logger.writeToFile(exc.getStackTrace());
        }
    }

    /** Add the entered tag to the image tags.*/
    public void addTagFunc() {
        if((view.getTagToAdd().getText() != null && !view.getTagToAdd().getText().isEmpty())) {
            //do all this if the field isn't empty
            image.addTag(view.getTagToAdd().getText());
            tm.addTag(view.getTagToAdd().getText()); /* add the tag to the list of tags, adds only if
                it doesn't already exist*/
            view.getTagToAdd().clear(); //clear the field
            setViewText(); //add to the display text
            view.setList(getAllTags()); //reset the list of available tags
        }
    }


    /** Remove the tag from the image tags */
    public void removeTagFunc() {
        //if the field isn't empty
        if((view.getTagToRemove().getText() != null && !view.getTagToRemove().getText().isEmpty())) {
            image.removeTag(view.getTagToRemove().getText()); //remove the tag from the images
            view.getTagToRemove().clear(); //clear this field
            setViewText(); //remove from the display box
        }
    }

    /** Set the scene to ImageManagerGUI */
    public void backButtonFunc() {
        Scene s= new Scene(new ImageManagerGUI(stage, directory), 500, 275);
        stage.setScene(s);
    }

    /** Sets the scene to an ImageHistoryGUI */
    public void revertFunc() {
        Scene s = new Scene (new ImageHistoryGUI(stage, directory, image), 500, 275);
        stage.setScene(s);
    }

    /** Create a directoryChooser and move to whichever directory is chosen */
    public void moveButtonFunc() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select a directory.");
            File dir = directoryChooser.showDialog(stage);
            String origin = imgFile.getAbsolutePath();
            String name = imgFile.getName();
            Path dest = Paths.get(dir.getAbsolutePath(), name);
            String destStr = dest.toString();
            filer.moveFile(origin, destStr); //moves the file to the destination
        }
        catch (NullPointerException exc) { //if cancel is clicked then this is thrown
            Logger.writeToFile(exc.getStackTrace());
        }
        Scene s = new Scene(new HomePageGUI(stage), 600, 465);
        stage.setScene(s); //change the scene back to HomePageGUI
    }


    /** Sets the scene to FilterGUI */
    public void applyFilterFunc() {
        Scene filterScene = new Scene (new FilterGUI(stage, directory, image), 500, 275);
        stage.setScene(filterScene);
    }

}
