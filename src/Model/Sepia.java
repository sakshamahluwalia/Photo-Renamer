package Model;

import java.awt.image.BufferedImage;

/**
 * This class is responsible for changing each pixel in an Image object
 * to make the image have a Sepia filter.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
// got the code from https://www.dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-sepia-image
public class Sepia implements Filter {

    /**
     * This method changes a pixel of the BufferedImage that has been inputted.
     * @param image BufferedImage
     * @param index1 int
     * @param index2 int
     */
    @Override
    public void loop(BufferedImage image, int index1, int index2) {

        int p = image.getRGB(index2,index1);

        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;

        //calculate tr, tg, tb
        int tr = (int)(0.393*r + 0.769*g + 0.189*b);
        int tg = (int)(0.349*r + 0.686*g + 0.168*b);
        int tb = (int)(0.272*r + 0.534*g + 0.131*b);

        //check condition
        if(tr > 255){
            r = 255;
        }else{
            r = tr;
        }

        if(tg > 255){
            g = 255;
        }else{
            g = tg;
        }

        if(tb > 255){
            b = 255;
        }else{
            b = tb;
        }

        //set new RGB value
        p = (a<<24) | (r<<16) | (g<<8) | b;

        image.setRGB(index2, index1, p);
    }
}