package Model;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Filter Interface
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public interface Filter {

    /**
     * An empty method that will be overridden in the sub classes of Filters.
     * @param img BufferedImage
     * @param index1 int
     * @param index2 int
     */
    void loop(BufferedImage img, int index1, int index2);
}
