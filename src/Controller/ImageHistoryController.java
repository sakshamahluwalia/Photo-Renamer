package Controller;


import Model.ImageManager;
import Model.Images;
import Model.Logger;
import View.ImageHistoryGUI;
import View.ImageTagGUI;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class ImageHistoryController {

    private Stage stage; //the stage we will be using
    private ImageHistoryGUI view; //the view for this controller
    private File directory; //the directory we are working in
    private Images image; //the image whose history we are viewing/modifying
    private String selectedHistory; //the history that has been selected in the listview
    private int index; //the index of the history that has been selected
    private ImageManager im; //this is created in order to access the history.ser file

    /** Creates a new ImageHistoryController
     *
     * @param s: the stage we are using
     * @param gui: the view we are using
     * @param dir: the directory we will be working in
     * @param img: the images object we will be using
     *
     * */
    public ImageHistoryController(Stage s, ImageHistoryGUI gui, File dir, Images img) {
        stage = s;
        view = gui;
        directory = dir;
        image = img;
        im = new ImageManager(); //this will initialize history to correspond to the serializable file
    }

    /** Sets the selectedHistory and index based on what the user has selected in the listView */
    public void listViewFunc() {
        try {
            String hist = view.getListView().getSelectionModel().getSelectedItem();
            selectedHistory = hist;
            index = view.getListView().getSelectionModel().getSelectedIndex();
        }
        catch(NullPointerException e) {
            Logger.writeToFile(e.getStackTrace());
        }
    }

    /** Will revert the image based on the listview selection and delete history after the selected index*/
    public void revertFunc() {
        if (! (selectedHistory == null)) {
            String oldName = image.getOldName(selectedHistory);
            image.removeHistory(index);
            image.update(oldName);
            backFunc(); //will go back to the ImageTagGUI screen
        }
    }

    /** Changes the scene to ImageTagGUI*/
    public void backFunc() {
        Scene scene = new Scene (new ImageTagGUI(stage, directory, image.getFile()), 600, 465);
        stage.setScene(scene);
    }


    /** Returns the history of this image
     *
     * @return the history of this image, so all the modifications it has undergone
     *
     * */
    public ArrayList<String> getHistory() {
        ArrayList<String> hist = image.getHistoryOfImage();
        if (hist == null) { //to avoid an error if the history does not exist for this image yet
            return new ArrayList();
        }
        else {
            return hist;
        }
    }
}
