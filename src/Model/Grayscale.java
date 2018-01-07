package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is responsible for changing each pixel in an Image object
 * to make the image GrayScale.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
//got the code from https://www.tutorialspoint.com/java_dip/grayscale_conversion.htm
public class Grayscale implements Filter {

    /**
     * This method changes a pixel of the BufferedImage that has been inputted.
     * @param image BufferedImage
     * @param index1 int
     * @param index2 int
     */
    @Override
    public void loop(BufferedImage image, int index1, int index2) {
        Color c = new Color(image.getRGB(index2, index1));
        int red = (int)(c.getRed() * 0.299);
        int green = (int)(c.getGreen() * 0.587);
        int blue = (int)(c.getBlue() *0.114);
        Color newColor = new Color(red+green+blue, red+green+blue, red+green+blue);
        image.setRGB(index2, index1, newColor.getRGB());
    }
}
