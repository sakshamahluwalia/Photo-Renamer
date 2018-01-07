package View;

import Controller.FilterController;
import Model.Images;
import Model.Logger;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.net.MalformedURLException;
/**
 * This class is responsible displaying the part of
 * the GUI that allows user to apply tags to an Images object.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class FilterGUI extends GridPane {

    /**
     * Displays image chosen from the directory.
     */
    private ImageView imageView2;

    /**
     * Allows the user to apply a filter to the Images object.
     */
    private Button applyFilter;


    /**
     * The contructor for this FilterGUI.
     * @param mainStage Stage
     * @param dir File
     * @param imgFile Images
     */
    public FilterGUI(Stage mainStage, File dir, Images imgFile) {

        FilterController controller = new FilterController(mainStage, this, dir, imgFile);

        Image image;
        try {
            image = new Image(imgFile.getFile().toURI().toURL().toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(225);

            imageView2 = new ImageView(image);
            imageView2.setFitWidth(200);
            imageView2.setFitHeight(225);

            Button sepia = new Button("Sepia");
            sepia.setTranslateX(56);
            sepia.setTranslateY(130);

            Button grayScale = new Button("GrayScale");
            grayScale.setTranslateX(116);
            grayScale.setTranslateY(130);

            Button backButton = new Button("Back");
            backButton.setTranslateX(0);
            backButton.setTranslateY(130);

            applyFilter = new Button("Apply Filter");
            applyFilter.setTranslateX(316);
            applyFilter.setTranslateY(130);
            applyFilter.setDisable(true);

            this.add(imageView, 0, 0);
            this.setHgap(5);
            this.add(imageView2, 1, 0);
            this.setPadding(new Insets(5, 0, 0, 20));

            this.getChildren().addAll(sepia, grayScale, backButton, applyFilter);

            grayScale.setOnAction(e -> controller.grayScale());
            sepia.setOnAction(e -> controller.sepiaFilter());
            applyFilter.setOnAction(e -> controller.applyFilter());
            backButton.setOnAction(e -> controller.backFunc());
        } catch (MalformedURLException e) {
            Logger.writeToFile(e.getStackTrace());
        }

        mainStage.setTitle("Edit Filters");
    }

    /**
     * Returns the ImageView imageView2 for FilterGUI
     * @return ImageView
     */
    public ImageView getImageView2() {
        return imageView2;
    }

    /**
     * Returns the button applyFilter for FilterGUI
     * @return ImageView
     */
    public Button getApplyFilter() {
        return applyFilter;
    }
}