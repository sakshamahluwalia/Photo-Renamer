package View;

import Controller.ImageHistoryController;
import Model.Images;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.File;

/**
 * This class is responsible for displaying the window
 * that allows users to choose the History of an Images object.
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class ImageHistoryGUI extends GridPane {

    /**
     * Displays a list of images in the selected directory.
     */
    private ListView<String> listView = new ListView();

    /**
     * The constructor for this class.
     * @param mainStage Stage
     * @param directory File
     * @param img Images
     */
    public ImageHistoryGUI(Stage mainStage, File directory, Images img) {

        ImageHistoryController ihc = new ImageHistoryController(mainStage, this, directory, img);

        ObservableList<String> items = FXCollections.observableArrayList (ihc.getHistory());
        listView.setItems(items);
        this.add(listView, 0, 0, 2, 1);

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 10, 10, 10));

        Button backButton = new Button("Back"); //local variable??
        this.add(backButton, 0, 1, 1, 1);

        Button revert = new Button("Revert");
        this.add(revert, 1, 1, 1, 1);

        listView.setOnMouseClicked(e -> ihc.listViewFunc());
        revert.setOnAction(e -> ihc.revertFunc());
        backButton.setOnAction(e -> ihc.backFunc());

        mainStage.setTitle("Image History");
    }

    /**
     * Returns the listView for ImageHistoryGUI.
     * @return ListView<String>
     */
    public ListView<String> getListView() {
        return listView;
    }

}