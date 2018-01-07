package Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is a container that contains only Images.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
class ImageList implements Iterable {

    /**
     * A list containing Images.
     */
    private ArrayList<Images> ImageList = new ArrayList<>();

    /**
     * Constructor for this class.
     *
     * @param ImageList ArrayList<Image> ImageList is an ArrayList containing elements
     *                  of type Images.
     */
    ImageList(ArrayList<Images> ImageList) {
        this.ImageList = ImageList;
    }

    @Override
    public Iterator iterator() {
        return getIterator();
    }

    /**
     * Returns the iterator for this class.
     *
     * @return  ImageListIterator<Images>
     */
    ImageListIterator<Images> getIterator() {
        return new ImageListIterator<>(ImageList);
    }

}
