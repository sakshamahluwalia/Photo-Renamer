package Controller;

import Model.Logger;
import View.*;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;

public class HomePageController{

    private Stage mainStage; //This is the GUI stage we are using

    /** Creates a new HomePageController
     *
     * @param stage: the stage we want to use
     *
     * */
    public HomePageController(Stage stage){
        mainStage = stage;
    }


    /** Creates a directory pop-up menu and changes the scene to an ImageManagerGUI if a directory is selected */
    public void directoryButtonFunc() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select a directory.");
            File directory = directoryChooser.showDialog(mainStage);
            //Pass the chosen directory into an ImageManagerGUI
            Scene imageScene = new Scene(new ImageManagerGUI(mainStage, directory), 500, 275);
            mainStage.setScene(imageScene);
        }
        //If the user clicks cancel for the directoryChooser there will be a NullPointerException
        catch (NullPointerException exc) {
            Logger.writeToFile(exc.getStackTrace());
        }
    }

    /** Changes the scene to a LoggerGUI*/
    public void viewHistoryFunc() {
        Scene historyScene = new Scene(new LoggerGUI(mainStage), 500, 275);
        mainStage.setScene(historyScene);
    }
}