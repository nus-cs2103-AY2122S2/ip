
package duke;

import duke.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * deals with loading and saving tasks in the file
 */
public class Storage {
    private String directory;
    private String filePath;

    /**
     * @param directory
     * @param filePath
     */
    public Storage(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    /**
     * rewrite the file with newly edited tasks after the user quits the app
     * @param listOfInputs
     * @throws IOException
     */
    public void reSavingFiles (ArrayList<Task> listOfInputs) throws IOException {
        //start saving the results for next return
        for (int i = 0; i < listOfInputs.size(); i++) {
            if (i == 0) {
                writeToFile(listOfInputs.get(i).message() + "\n", filePath);
            } else {
                listOfInputs.get(i).updateData(filePath);
                //updateData(listOfInputs.get(i).message(), filePath);

            }
        }
    }

    /**
     * read data from file
     * @param filePath
     */
    public String readData(String filePath) {
        String message = "";
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                 message = message + sc.nextLine();
            }
        } catch (FileNotFoundException e) {
           return message + "Cannot read data!!!";
        }
        return message;
    }

    /**
     * rewrite the file
     * @param textToAdd
     * @param filePath
     * @throws IOException
     */
    private static void writeToFile(String textToAdd, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    /**
     * update newly edited tasks into the file
     * @param message
     * @param filePath
     * @throws IOException
     */
    public void updateData(String message, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("\n" + message);
        //fw.write(message + "\n");
        fw.close();

    }




}