package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
/**
 * This class represents an image file in a directory.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class Images {

    /**
     * Path for this file.
     */
    private String dir;

    /**
     * Name of this image.
     */
    private String fileName;

    /**
     * The Image file.
     */
    private File file;

    /**
     * The extension of this file.
     */
    private String extension;

    /**
     * The original name of this file.
     */
    private String originalName;

    /**
     * Keeps a track of all tags associated with this Image.
     */
    private ArrayList<String> currentTags = new ArrayList<>();

    /**
     * A tagManager Object.
     */
    private TagManager tagManager = new TagManager();

    /**
     * Constructor for this class.
     *
     * @param filePath String the name of this image.jpg file.
     */
    public Images(String filePath) {
        setFile( new File(filePath) );
        setDir(file.getParent());
        setFileName( extractName() );
        setExtension( extractExtension() );
        extractTags();
    }

    /**
     * Method to remove the extension from the name
     * @return String trimmed filename
     */
    private String extractName() {
        if (file.getName().contains(".")) {
            return file.getName().substring(0, file.getName().lastIndexOf("."));
        }
        return "";
    }

    /**
     * Returns the extension of the Images object.
     * @return String
     */
    private String extractExtension() {
        if(file.getName().contains(".")) {
            return file.getName().substring(file.getName().lastIndexOf("."));
        }
        return "";
    }

    /**
     * Populates currentTags with tags in the Images.fileName.
     */
    private void extractTags() {
        if (fileName.contains("@")) {
            String[] tags = fileName.split("@");
            this.originalName = tags[0];
            for (int i = 1; i <= tags.length-1; i++) {
                currentTags.add(tags[i].trim());
                tagManager.addTag(tags[i].trim());
            }
        } else {
            this.originalName = fileName;
        }
    }

    /**
     * Updating the name to include a tag.
     *
     * @param tag String the tag to be added.
     */
    public void addTag(String tag) {
        if (!(checkTags(tag))) {
            //TagManager.addTag(tag);this method should be renamed to create tag since it will be called to create a new tag.
            currentTags.add(tag);
            String name = this.fileName + " @" + tag;
            update(name);
        }
    }

    /**
     * Updating the name to remove a tag.
     *
     * @param tag String the tag to be removed.
     */
    public void removeTag(String tag) {
        if (checkTags(tag)) {
            currentTags.remove(tag);
            String[] name = fileName.split("@");
            StringBuilder newName = new StringBuilder(name[0].trim());
            for (int i = 1; i <= name.length - 1; i++) {
                if (!(name[i].trim().equals(tag))) {
                    newName.append(" @" + name[i].trim());
                }
            }
            update(newName.toString());
        }
    }

    /**
     * Records the changed Name, changes the fileName and renames the file in the system.
     * @param newName The name that is going to replace the previous fileName.
     */
    public void update(String newName) {
        recordChange(this.getFileName(), newName);
        setFileName(newName);
        rename();
    }

    /**
     * Return true if Tag is in tags.
     *
     * @param Tag The tag to check.
     * @return boolean
     */
    private boolean checkTags(String Tag) {
        return (currentTags.contains(Tag));
    }

    /**
     * Renames the file in the OS.
     */
    private void rename() {
        File newFile = new File(dir+"/"+fileName+extension);
        if (file.renameTo(newFile)) {
            setFile(newFile);
        }
    }

    /**
     * Keeps track of the changes in the Name.
     * @param oldName The previous name
     * @param newName The updated name.
     */
    private void recordChange(String oldName, String newName) {
        String change = "Oldname: " + oldName.trim() + " Newname: " + newName.trim();
        Logger.writeToFile(change);
        ImageManager.addHistory(originalName, change);
    }

    /**
     * This function applies a filter to the Images object.
     * @param filter Filters
     */
    public void applyFilter(Filters filter) {
        BufferedImage img = filter.convert(this.getFile());
        if (img != null) {
            filter.writeImage(img, this.getDir()+"/"+this.originalName+" "+
                    filter.getFilter().getClass().getSimpleName()+this.getExtension(), this.getExtension());
        }
    }

    /**
     * This method returns an ArrayList containing all the history of this Images object.
     * @return ArrayList<String>
     */
    public ArrayList<String> getHistoryOfImage() {
        return ImageManager.history.get(originalName.trim());
    }

    /**
     * This function removes the history after a certain index.
     * @param index int
     */
    public void removeHistory(int index) {
        ImageManager.history.get(originalName.trim()).
                subList(index, ImageManager.history.get(originalName.trim()).size()).clear();
    }

    /**
     * This function returns the oldName from an item present in this Image history.
     * @param change String
     * @return String
     */
    public String getOldName(String change) {
        String[] parsed = change.split(":");
        return parsed[1].replaceFirst(" Newname", " ").trim();
    }

    /**
     * Update the fileName to the fileName specified.
     * @param fileName String
     */
    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Update the file to the file given.
     * @param file File
     */
    private void setFile(File file) {
        this.file = file;
    }

    /**
     * Return the file linked to the image
     * @return File
     */
    public File getFile() {
        return file;
    }

    /**
     * Returns the directory this Images object is in.
     * @return
     */
    public String getDir() { return this.dir; }
    /**
     * Update the dir to the specified dir
     * @param dir String
     */
    private void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * Update the extension to the specified extension
     * @param extension String
     */
    private void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Return the currentTags
     * @return ArrayList<String>
     */
    public ArrayList<String> getCurrentTags() {
        return currentTags;
    }

    /**
     * Return fileName
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Return extension
     * @return String
     */
    String getExtension() {
        return extension;
    }
}