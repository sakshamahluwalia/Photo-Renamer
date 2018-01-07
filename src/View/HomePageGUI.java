package View;

import Controller.HomePageController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is responsible for displaying
 * the Home page of the GUI.
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class HomePageGUI extends GridPane { //added

    /**
     * The constructor for HomePageGUI
     * @param mainStage The setting for the UI to build on
     */
    public HomePageGUI(Stage mainStage){
        this.setAlignment(Pos.CENTER);

        // Create and set opening text
        Text t = new Text(0, 0, "Welcome to the image manager.");
        t.setTranslateX(0);
        t.setTranslateY(0);

        // Set file directory button in scene
        Button directoryButton = new Button("Select your directory.");
        directoryButton.setTranslateX(20);
        directoryButton.setTranslateY(30);

        //Set view history button in scene
        Button viewHistory = new Button("View Log History");
        viewHistory.setTranslateX(35);
        viewHistory.setTranslateY(60);

        // Add text and button to GridPane
        this.getChildren().addAll(t, directoryButton, viewHistory);

        //Calls HomePageController to handle chosen directory
        HomePageController hpc = new HomePageController(mainStage);
        directoryButton.setOnAction((event) -> hpc.directoryButtonFunc());
        viewHistory.setOnAction((event) -> hpc.viewHistoryFunc());

        //Change title of the screen
        mainStage.setTitle("Image Manager");
    }

}