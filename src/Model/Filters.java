package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class is responsible for applying filters to
 * an Images object.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class Filters {

    /**
     * The Filters object to apply to the Images object.
     */
    private Filter filter;

    /**
     * The constructor for this class. Sets a Filters object to filter.
     * @param filter Filters
     */
    public Filters(Filter filter) {
        this.filter = filter;
    }

    /**
     * This function applies the filter to the File object.
     * @param file File
     * @return BufferedImage
     */
    //got the code from https://www.tutorialspoint.com/java_dip/grayscale_conversion.htm
    BufferedImage convert(File file) {

        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            for(int index1=0; index1<height; index1++){
                for(int index2=0; index2<width; index2++){

                    this.filter.loop(image, index1, index2);

                }
            }
            return image;
        } catch (Exception e) {
            Logger.writeToFile(e.getStackTrace());
            return null;
        }
    }

    /**
     * This function is responsible for making a new File.
     * @param image BufferedImage
     * @param path String
     * @param extension String
     */
    void writeImage(BufferedImage image, String path, String extension) {
        File output = new File(path);
        try{
            ImageIO.write(image, extension.replace(".", " ").trim(), output);
        } catch (Exception e) {
            Logger.writeToFile(e.getStackTrace());
        }
    }

    /**
     * Returns the filter
     * @return Filters
     */
    Filter getFilter() {
        return filter;
    }
}
