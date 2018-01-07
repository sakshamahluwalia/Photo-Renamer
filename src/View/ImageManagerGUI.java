package View;

import Controller.ImageManagerController;
import Model.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;

import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * This class is responsible for displaying the window
 * that allows users to choose to update the tags of an image.
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */

public class ImageManagerGUI extends GridPane {

    /**
     * Displays a list of images in the selected directory.
     */
    private ListView <String> list = new ListView<>();

    /**
     * Displays image chosen from the directory.
     */
    private ImageView iv = new ImageView();

    /**
     * Contains list of all images in directory
     * selected, and if applicable, its subdirectories.
     */
    private ObservableList<String> items;


    /**
     * The constructor for ImageManagerGUI
     * @param mainStage The setting for the UI to build on
     * @param directory The directory chosen by the user
     */
    public ImageManagerGUI(Stage mainStage, File directory) {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(30, 0, 40, 0));

        //Creates ImageManagerController to handle events and call back-end functions
        ImageManagerController imc = new ImageManagerController(mainStage, this, directory);

        /*This code was adapted from:
        https://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF
         */
        // Creates list to display image names
        items = FXCollections.observableArrayList (imc.getImageFiles());
        list.setItems(items);
        this.add(list, 0, 3, 2, 1);

        //Starts with preset image and then changes once image is selected
        iv.setFitWidth(175);
        iv.setFitHeight(175);
        this.add(iv, 3, 3);

        //Takes user to new window to update tags for the image
        Button updateTag = new Button("Update Image");
        this.add(updateTag, 3, 5, 1, 1);

        //Takes user back to home page
        Button backButton = new Button("Back");
        this.add(backButton, 0, 5, 1, 1);

        //Takes user to new window to update the tag database
        Button updateList = new Button("Update Tag List");
        this.add(updateList, 1, 5, 1, 1);

        //Allows user to see/not see images in any subdirectories
        Button toggle = new Button("View Subdirectories");
        this.add(toggle, 0, 1, 1, 1);
        this.setHalignment(toggle, HPos.RIGHT);


        //Event handlers
        list.setOnMouseClicked(e -> imc.listFunc());
        updateTag.setOnAction(e -> imc.updateTagFunc());
        backButton.setOnAction(e -> imc.backButtonFunc());
        updateList.setOnAction(e -> imc.updateListFunc());
        toggle.setOnAction(e -> imc.toggleFunc());

        //Change title of the screen
        mainStage.setTitle("Image Selector");
    }

    /**
     * Returns list
     * @return ListView<String>
     */
    public ListView<String> getList() {
        return list;
    }

    /**
     * Sets the ImageView Iv to display an Image.
     * @param f File
     * @throws MalformedURLException e
     */
    public void setIv(File f) throws MalformedURLException {
        try{
            iv.setImage(new Image(f.toURI().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            Logger.writeToFile(e.getStackTrace());
        }
    }

    /**
     * Sets the ImageView Iv to display an Image.
     * @param lst ArrayList<String>
     */
    public void setListView(ArrayList<String> lst) {
        items = FXCollections.observableArrayList (lst);
        list.setItems(items);
    }
}
