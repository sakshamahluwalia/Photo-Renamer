package Model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for iterating over a List.
 *
 * Implements Iterator
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
class ImageListIterator<T> implements Iterator {

    /**
     * The ArrayList to iterate over.
     */
    private ArrayList<T> ImageList;

    /**
     * The current index of the list.
     */
    private int currindex;

    /**
     * Constructor for this class.
     *
     * @param ImageList ArrayList<T> ImageList is an ArrayList
     *                  of type T. 'T' will usually be of type Image.
     */
    ImageListIterator(ArrayList<T> ImageList) {
        this.ImageList = ImageList;
    }

    /**
     * Tells us if the current index is the last index of ImageList.
     *
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        return (currindex < ImageList.size());
    }

    /**
     * Returns the next element of ImageList if it is not null.
     *
     * @return T
     */
    @Override
    public T next() {
        if (hasNext()) {
            return ImageList.get(currindex++);
        }
        return null;
    }

    /**
     * Returns the current value of currIndex
     * @return int
     */
    int getCurrindex() {
        return currindex;
    }
}