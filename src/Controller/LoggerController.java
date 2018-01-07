package Controller;

import View.*;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import Model.Logger;

public class LoggerController {

    private Stage stage; //the stage we are using

    /** Creates a new LoggerController
    *
    * @param s: the stage we will be using
    *
    * */
    public LoggerController(Stage s) {
        stage = s;
    }

    /** Gets the history of all renaming
     *
     * @return the renaming history
     * */
    public String generateHistory() {
        return Logger.readFile();
    }

    /** Set the scene to a HomePageGUI */
    public void backButtonFunc() {
        Scene homeScene = new Scene(new HomePageGUI(stage), 500, 275);
        stage.setScene(homeScene);
    }







}
