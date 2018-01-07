package Model;

import java.util.*;

/**
 * This class is responsible for updating the Images object.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class ImageManager implements Observers {

    /**
     * Filer Object will be used to get ImageList.
     */
    private Filer filer;

    /**
     * This HashMap will be used to store the newNames as well as the oldNames of the Files.
     */
    static HashMap<String, ArrayList<String>> history = new HashMap<>();

    private static Serializer<HashMap<String, ArrayList<String>>> serializer = new Serializer<>(history);

    /**
     * The constructor of this class.
     */
    public ImageManager() {
        history = serializer.deserialize("history.ser");
    }

    /**
     * Remove the tag from the images present in the list.
     * @param tag_ String tag_ to remove.
     */
    public void update(Object tag_) { //remove specified tag from images in directory.
        String tag = (String)tag_;
        ImageList imgList = new ImageList(filer.getAllFilesInDir());
        ImageListIterator<Images> iter = imgList.getIterator();
        while(iter.hasNext()) {
            Images img = iter.next();
            img.removeTag(tag);
        }
    }

    /**
     * This method updates history so that it keeps track of the
     * changes in filenames.
     * @param fileName String the original filename
     * @param change String text to append to the history.
     */
    static void addHistory(String fileName, String change) {
        if (history.containsKey(fileName)) {
            history.get(fileName).add(change);
        } else {
            history.put(fileName, new ArrayList<String>());
            history.get(fileName).add(change);
        }
        serializer.serialize("history.ser");
    }

    /**
     * Set the filer's directory to dir
     * @param dir String
     */
    public void setDirectory(String dir) {
        this.filer = new Filer(dir);
    }
}