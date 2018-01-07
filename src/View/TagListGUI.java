package View;

import Controller.TagListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * This class is responsible displaying the tag database.
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */

public class TagListGUI extends GridPane {

    /**
     * Displays a text field to add a tag to the database
     */
    private TextField addTag = new TextField();

    /**
     * Displays a text field to remove a tag from the database
     */
    private TextField removeTag = new TextField();

    /**
     * Displays a list of tags in the database
     */
    private ListView<String> list = new ListView<>();

    /**
     * The constructor for this class
     * @param mainStage The setting for the UI to build on
     */
    public TagListGUI(Stage mainStage, File directory) {
        //Creates controllers for TagListGUI
        TagListController tlc = new TagListController(mainStage, this, directory);

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 10, 0, 10));

        //Creates and sets title of the list of tags
        Text editTags = new Text("List of Tags Available");
        this.add(editTags, 0, 0, 1, 1);

        /*This code was adapted from:
        https://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF
         */

        ObservableList<String> items = FXCollections.observableArrayList (tlc.getTagList());
        list.setItems(items);
        this.add(list, 0, 1, 2, 4);

        //Creates and sets text for adding and removing tags
        Text add = new Text("Add:");
        this.add(add, 2, 1, 1, 1);
        Text remove = new Text("Remove:");
        this.add(remove, 2, 2, 1, 1);


        this.add(addTag, 3, 1);


        this.add(removeTag, 3, 2);

        //Creates and sets backButton.
        Button backButton = new Button("Back");
        this.add(backButton, 0, 5, 2, 5);


        //Event handlers
        addTag.setOnKeyPressed(e -> tlc.addTagFunc(e.getCode()));
        removeTag.setOnKeyPressed(e -> tlc.removeTagFunc(e.getCode()));
        backButton.setOnAction(e -> tlc.backButtonFunc());

        mainStage.setTitle("Tag Database");

    }

    /**
     * Returns the addTag TextField for TagListGUI.
     * @return TextField
     */
    public TextField getAddTag() {
        return addTag;
    }

    /**
     * Returns the removeTag TextField for TagListGUI.
     * @return TextField
     */
    public TextField getRemoveTag() {
        return removeTag;
    }

    /**
     * Updates the tag list once a tag has been added or removed
     * @param tags A list of tags in the database
     */
    public void changeList(ArrayList<String> tags) {
        ObservableList<String> items = FXCollections.observableArrayList (tags);
        list.setItems(items);
    }

}
