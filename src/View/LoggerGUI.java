package View;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import Controller.LoggerController;
import javafx.scene.text.Text;

/**
 * This class is responsible for displaying the window
 * that allows users to look through the masterlog.
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class LoggerGUI extends GridPane{

    /**
     * The constructor for this file.
     * @param mainStage Stage
     */
    public LoggerGUI (Stage mainStage) {
        LoggerController lc = new LoggerController(mainStage);

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 10, 0, 10));

        Button backButton;
        backButton = new Button("Back");

        TextArea history = new TextArea();
        history.setEditable(false);
        String items = lc.generateHistory();
        history.setText(items);

        Text label = new Text("Master Log");

        this.add(label, 0,0);
        this.add(backButton, 0, 2, 1, 1);
        this.add(history,0,1);

        backButton.setOnAction(e -> lc.backButtonFunc());

        mainStage.setTitle("Logger History");
    }

}