package Model;

import java.io.*;
import java.time.LocalDateTime;

/**
 * This class is responsible for logging changes whenever
 * a file name is changed.
 *
 * @author Martin Baroody
 * @author Jai Aggarwal
 * @author Aleksa Zatezalo
 * @author Saksham Ahluwalia
 */
public class Logger {

    /**
     * This function writes the changes to a file.
     * @param change String The input to write to the file
     */
    static void writeToFile(String change) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));
            writer.write(change + ", " + LocalDateTime.now() + System.lineSeparator());
            writer.close();
        } catch (IOException error) {
            writeToFile(error.getMessage());
        }
    }

    /**
     * This function logs the error to ErrorLog.txt
     * @param e StackTraceElement
     */
    public static void writeToFile(StackTraceElement[] e) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ErrorLog.txt", true));
            for (Object obj: e) {
                writer.write(obj + System.lineSeparator());
            }
            writer.write("<--End of Message-->" + System.lineSeparator());
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * This function is responsible for parsing the logger.txt.
     * @return String
     */
    public static String readFile() {
        StringBuilder builder = new StringBuilder();

        // This will reference one line at a time
        String line;
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("logger.txt"));
            while((line = bufferedReader.readLine()) != null) {
                builder.append(line.trim() + System.lineSeparator());
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(IOException ex) {
            writeToFile(ex.getStackTrace());
        }
        return builder.toString();
    }


}
