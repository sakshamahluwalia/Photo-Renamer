package Controller;

import Model.Filter;
import Model.Filters;
import Model.Images;
import Model.Grayscale;
import Model.Sepia;
import View.FilterGUI;
import View.ImageTagGUI;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.stage.Stage;

import java.io.File;

/** This is a controller for the FilterGUI */

public class FilterController {

    private Images image; //the image we are applying filters to
    private Stage stage; //the stage on which the program will be set
    private File directory; //the current directory we are working in
    private FilterGUI view; //the view that this controls
    private ColorAdjust desaturate = new ColorAdjust(); //for applying grayscale
    private SepiaTone sepiaTone = new SepiaTone(); //for applying sepia
    private Filter filter; //the Filter that has been selected

    /** Creates a new FilterController
     *
     * @param s: the stage we will be using
     * @param gui: the FilterGUI we will be controlling
     * @param dir: the directory we are working in
     * @param image: the image we will be applying filters to.
     *
     * */

    public FilterController(Stage s, FilterGUI gui, File dir, Images image) {
        this.image = image;
        view = gui;
        stage = s;
        directory = dir;
        desaturate.setSaturation(-1);
    }


    /** Sets filter to sepia */
    public void sepiaFilter() {
        setFilter(new Sepia());
        view.getApplyFilter().setDisable(false); //we enable the apply filter button
        view.getImageView2().setEffect(sepiaTone); //we preview the sepia filter on the image
    }

    /** Sets filter to grayscale */
    public void grayScale() {
        setFilter(new Grayscale());
        view.getApplyFilter().setDisable(false); //we enable the apply filter button
        view.getImageView2().setEffect(desaturate); //we preview the greyscale filter on the image
    }


    /** Creates a new version of the image with the selected filter applied to it */
    public void applyFilter() {
        image.applyFilter(new Filters(filter));
        backFunc(); //Go back to an ImageTagGUI
    }

    /** Go back to an ImageTagGUI*/
    public void backFunc() {
        Scene imageTagScene = new Scene (new ImageTagGUI(stage, directory, image.getFile()), 600, 465);
        stage.setScene(imageTagScene);
    }

    /** Sets the filter variable
     *
     * @param filter: sets the filter instance variable to this argument
     * */
    private void setFilter(Filter filter) {
        this.filter = filter;
    }
}
