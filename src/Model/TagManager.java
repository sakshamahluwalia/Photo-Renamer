package Model;

import java.util.ArrayList;

/**
 * This class is responsible for keeping track of all the
 * tags.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class TagManager extends Subject {

    /**
     * The list containing all tags.
     */
    private static ArrayList<String> tagList = new ArrayList<>();

    private static Serializer<ArrayList<String>> serializer = new Serializer<>(tagList);

    /**
     * The constructor for this class.
     */
    public TagManager() {
        tagList = serializer.deserialize("tags.ser");
        //We will store the tagList into tags.txt
        //We will deserialize it to retrieve the information

    }

    /**
     * Returns the taglist
     * @return ArrayList<String>
     */
    public ArrayList<String> getTagList() {
        return tagList;
    }

    /**
     * Adds a specified tag to the tagList.
     * @param tag String
     */
    public void addTag(String tag) {
        if (!tagList.contains(tag)) {
            tagList.add(tag);
            writeToFile();
        }
    }

    /**
     * Remove the specified tag from the tagList and update the
     * images by removing the specified tag in them.
     * @param tag String
     */
    //Returns true if removal was successful, else false.
    public void removeTag(String tag) {
        if(tagList.contains(tag)) {
            notifyObservers(tag); //We want to remove the tag from all images in the directory with this tag
            tagList.remove(tag);
            writeToFile();
        }
    }

    /**
     * Serializes the tagList.
     */
    private void writeToFile(){
        serializer.serialize("tags.ser");
    }
}