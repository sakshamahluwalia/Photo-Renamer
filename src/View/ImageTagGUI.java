package View;

import Controller.ImageTagController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
/**
 * This class is responsible displaying the part of
 * the GUI that allows user to edit an image's tags.
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */

public class ImageTagGUI extends GridPane {

    /**
     * Displays a list of tags to be used for image
     */
    private ListView<String> list = new ListView<>();

    /**
     * Space for users to write a tag to be added
     */
    private TextField tagToAdd = new TextField();

    /**
     *Space for users to write a tag to be removed
     */
    private TextField tagToRemove = new TextField();

    /**
     * Displays the tags currently added to the selected image
     */
    private TextArea tagsText = new TextArea();

    /**
     * The constructor for this class
     * @param mainStage The setting for the UI to build on
     * @param dir The directory chosen by the user
     * @param imgFile The image being edited
     */
    public ImageTagGUI(Stage mainStage, File dir, File imgFile) {

        //Creates controller for ImageTagGUI
        ImageTagController itc = new ImageTagController(mainStage, this, dir, imgFile);

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(35, 20, 35, 20));

        //Title for list of tags
        Text editTags = new Text("Existing Tags");
        this.add(editTags, 0, 0, 1, 1);
        /*This code was adapted from:
        https://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF
         */

        setList(itc.getAllTags());
        this.add(list, 0, 1, 3, 1);


        //Creates and sets display for images
        ImageView imageView = new ImageView();

        imageView.setFitWidth(200);
        imageView.setFitHeight(250);
        try {
            String imageURL = itc.getImgFile().toURI().toURL().toExternalForm();
            imageView.setImage(new Image(imageURL));
        }
        catch(MalformedURLException e) {
            System.out.println("Error");
        }

        this.add(imageView, 3, 1,3,1);

        itc.setViewText();
        this.add(tagsText, 0, 3, 3, 1);
        tagsText.setEditable(false);


        this.add(tagToAdd, 0, 4, 3, 1);


        Button addTag = new Button("Add Tag");
        this.add(addTag, 3, 4,3,1);


        this.add(tagToRemove, 0, 6, 3, 1);

        Button removeTag = new Button("Remove Tag");
        this.add(removeTag, 3, 6,3,1);


        Button revertTag = new Button("Revert Tag");
        this.add(revertTag, 2, 7, 1, 1);

        Button backButton = new Button("Back");
        this.add(backButton, 0, 7, 1, 1);


        Button moveButton = new Button("Move");
        this.add(moveButton, 3, 7, 3, 1);
//        moveButton.setTranslateX(300);
//        moveButton.setTranslateY(440);

        Button applyFilter = new Button("Apply Filter");
        this.add(applyFilter, 3, 3, 1, 1);
        mainStage.setTitle(imgFile.getAbsolutePath());


//        this.getChildren().addAll(applyFilter, moveButton);

        // Event handlers
        list.setOnMouseClicked(e -> itc.listFunc());
        addTag.setOnAction(e -> itc.addTagFunc());
        removeTag.setOnAction(e -> itc.removeTagFunc());
        backButton.setOnAction(e -> itc.backButtonFunc());
        moveButton.setOnAction(e -> itc.moveButtonFunc());
        revertTag.setOnAction(e -> itc.revertFunc());
        applyFilter.setOnAction(e -> itc.applyFilterFunc());
    }

    /**
     * Returns the ListView list for ImageTagGUI.
     * @return ListView
     */
    public ListView<String> getList() {
        return list;
    }

    /**
     * Returns the TextField tagToAdd for ImageTagGUI.
     * @return TextField
     */
    public TextField getTagToAdd() {
        return tagToAdd;
    }

    /**
     * Returns the TextField tagtoRemove for ImageTagGUI.
     * @return TextField
     */
    public TextField getTagToRemove() {
        return tagToRemove;
    }

    /**
     * Sets the tagsText to the specified String
     * @param str String
     */
    public void setTagsText(String str) {
        tagsText.setText(str);
    }

    /**
     * Sets the list to the specified lst.
     * @param lst ArrayList<String>
     */
    public void setList(ArrayList<String> lst) {
        ObservableList<String> items = FXCollections.observableArrayList (lst);
        list.setItems(items);

    }

}

