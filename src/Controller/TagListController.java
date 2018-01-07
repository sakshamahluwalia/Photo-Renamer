package Controller;

import Model.ImageManager;
import View.*;
import Model.TagManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.util.*;

public class TagListController{

    private TagManager tm; //to access the tag database
    private TagListGUI tagView; //the view
    private Stage stage; //the stage we are using
    private ImageManager im; /* we are using this in order for the Observer Design Pattern to be applied so that the
    removed tags delete from the files in the directory and subdirectories*/
    private File directory; //the directory we are working in

    /** Creates a new TagListController
     *
     * @param s: the stage
     * @param view: the view to be controlled
     * @param dir: the working directory
     *
     * */
    public TagListController(Stage s, TagListGUI view, File dir) {
        stage = s;
        tm = new TagManager();
        tagView = view;
        directory = dir;
        im = new ImageManager();
        im.setDirectory(directory.getAbsolutePath());
        tm.addObserver(im); /* adding the image manager to the list of observers so that the design pattern can be
        applied */
    }

    /** Returns the list of all tags
     *
     * @return list of all tags
     * */
    public ArrayList<String> getTagList() {
        return tm.getTagList();
    }

    /** adds a tag to the list of tags
     *
     * @param kc: the key that was pressed in the field
     * */
    public void addTagFunc(KeyCode kc) {
        if (kc.equals(KeyCode.ENTER)) {
            //if enter is pressed on the addTag field
            //in this case we add the specified tag to the list of tags
            if ((tagView.getAddTag().getText() != null && !tagView.getAddTag().getText().isEmpty())) {
                //if the field wasn't empty
                tm.addTag(tagView.getAddTag().getText());
            }
            tagView.getAddTag().clear(); //we clear the field
            tagView.changeList(getTagList()); //we update the list of tags on the GUI
        }
    }


    /** removes a tag from the list of tags
     *
     * @param kc: the key that was pressed in the field
     * */
    public void removeTagFunc(KeyCode kc) {
        if (kc.equals(KeyCode.ENTER)) {
            //if enter is pressed on the removeTag field
            //remove the specified tag from the list of tags
            if ((tagView.getRemoveTag().getText() != null && !tagView.getRemoveTag().getText().isEmpty())) {
                tm.removeTag(tagView.getRemoveTag().getText());
            }
            tagView.getRemoveTag().clear(); //clear the field
            tagView.changeList(getTagList()); //update the list of tags on the GUI
        }

    }

    /** Change the scene to a HomePageGUI */
    public void backButtonFunc() {
        Scene s = new Scene(new ImageManagerGUI(stage, directory), 500, 275);
        stage.setScene(s);
    }

}
