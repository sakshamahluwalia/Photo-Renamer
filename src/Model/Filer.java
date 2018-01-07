package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is responsible for retrieving files from a directory
 * and its sub-directories
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class Filer {

    /**
     * The directory to work with.
     */
    private File directory;

    /**
     * This ArrayList contains the common Image extension.
     */
    private final ArrayList<String> extensions = new ArrayList<>(Arrays.asList(".png", ".jpg", ".jpeg", ".PNG"));

    /**
     * The constructor for this class
     * @param directory The directory to work in.
     */
    public Filer(String directory) {
        this.directory = new File(directory);
    }

    /**
     * Generates a list of all the file names in the directory and subdirectories
     * @param dir The directory to work in
     * @param images ArrayList to add the Image object to
     *
     */
    //Code adapted from https://stackoverflow.com/questions/14676407/list-all-files-in-the-folder-and-also-sub-folders
    private void listFiles(File dir, ArrayList<Images> images) {
        File[] fileList = dir.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    Images img = new Images(file.getAbsolutePath());
                    if (extensions.contains(img.getExtension())) {
                        images.add(img);
                    }
                }
            }
        }
    }

    /**
     * Calls listFiles to get a list of Images in the directory.
     *
     * @return ImageList containing instances of Images.
     */
    public ArrayList<Images> getImageList() {
        ArrayList<Images> files = new ArrayList<>(); //this will be the list of files
        listFiles(directory, files); //add all files in the directory to the list of files
        return files;
    }

    /**
     * Moves a file only if the file is in a different directory.
     * @param origin The directory the file is currently in.
     * @param dest The directory to move the file in.
     *
     */
    public void moveFile(String origin, String dest) { //dest should be an absolute path.
        File f = new File(origin);
        f.renameTo(new File(dest)); /*note this only moves the file if the file is in a different
        directory */
    }

    /**
     * This function returns all the images under the directory of this Filer.
     * @return ArrayList<Images>
     */
    private ArrayList<Images> getImagesUnderDir() {
        File[] files = directory.listFiles();
        ArrayList<Images> underDir = new ArrayList<>();
        //Gets all the images under the folders.
        if (files != null) {
            for (File folder: files) {
                if (folder.isDirectory()) {
                    listFiles(folder, underDir);
                }
            }
        }
        return underDir;
    }

    /**
     * Return a list containing image names present in this Filer's directory.
     * @return ArrayList<String>
     */
    public ArrayList<String> getFileNames(ArrayList<Images> images) {
        //As the parameter we can enter either the return of getImageList or getImagesUnderDir
        ImageList il = new ImageList(images);
        ArrayList<String> names = new ArrayList<>();
        ImageListIterator<Images> iter = il.getIterator();
        while(iter.hasNext()) {
            Images img = iter.next();
            names.add(img.getFileName());
        }
        return names;
    }

    /**
     * Return the Image at the specified index.
     * @param index int
     * @return Images Object
     */
    public Images getImageAtIndex(int index) {
        ImageList il = new ImageList(getAllFilesInDir());
        ImageListIterator<Images> iter = il.getIterator();
        while(iter.hasNext()) {
            Images img = iter.next();
            if (iter.getCurrindex() -1 == index) {
                return img;
            }
        }
        return null;
    }

    /**
     * This function returns all the images in and under the Filer's directory.
     * @return ArrayList<Images>
     */
    public ArrayList<Images> getAllFilesInDir() {
        ArrayList<Images> images = getImageList();
        images.addAll(getImagesUnderDir());
        return images;
    }
}
