package Model;

import java.io.*;

class Serializer<T> {

    private T obj;

    Serializer(T obj) {
        this.obj = obj;
    }

    /**
     * The method writes the ArrayList Object to a file.
     * @param fileName File to write to.
     */
    /*This code was taken from
        https://stackoverflow.com/questions/17293991/how-to-write-and-read-java-serialized-objects-into-a-file
     */
    void serialize(String fileName) {
        //note that filename must end in .ser
        try {
            File f = new File(fileName);
            f.createNewFile(); //does nothing if the file already exists. Else creates a new file with this name
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(obj);
            oos.close();
            fout.close();
        }
        catch(IOException e) {
            System.out.println("An error occurred during serialization");
        }
    }

    /**
     * Retrieve the object from a serialised file
     * @param fileName String
     * @return ArrayList<String>
     */
    T deserialize(String fileName){
        try {
            File f = new File(fileName);
            if(f.isFile()) {
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                obj = (T) in.readObject(); //NOTE: will fail if we didn't serialize and arraylist
                //deserialize should be used only after serialize
                in.close();
                fileIn.close();

            }
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred during serialization");
        }
        return obj;
    }
}
