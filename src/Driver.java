import View.HomePageGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {}

    @Override
    public void start(Stage mainStage) throws Exception {
        final Scene scene = new Scene(new HomePageGUI(mainStage), 500, 275);
        mainStage.setTitle("Image Manager");
        mainStage.setScene(scene);
        mainStage.show();
    }
}
