package duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the storage class which is responsible
 * to store different tasks into a specific file or
 * to read different tasks from a specific file.
 */
class Storage {
    private static String HOME = System.getProperty("user.home");
    private ArrayList<Task> taskArray;
    Storage() {
        taskArray = new ArrayList<Task>();
    }

    /**
     * Loads the different tasks from a specific file.
     * If the file does not exist at the given path,
     * a new file will then be created.
     * @return the loaded file.
     */
    public File load() {
        FileClass fc = new FileClass(); //file class
        String homePath = HOME + "/data";
        fc.createDirectory(homePath); //create directory first
        fc.createFile(homePath); //create a file in the /home/data/tasks.txt
        File file = new File(homePath);
        return file;
    }

    /**
     * Writes the output to a specific file.
     * If the file does not exist at the given path,
     * a new file will then be created.
     */
    public void writeFile() {
        FileClass fc = new FileClass();
        String filePath = HOME + "/data/stored.txt";
        fc.createFile(filePath);
        for (int i = 0; i < taskArray.size(); i++) {
            Task tasks = taskArray.get(i);
            try {
                String firstInitial = tasks.getInitial(); //first initial character
                String textToAdd = firstInitial + " | " + tasks.getStatusIcon() + " | "
                        + taskArray.get(i).getDescription();
                fc.writeFile(filePath, textToAdd);
            } catch (IOException e) {
                System.out.println("File is not found :(!");
            }

        }
    }
    /**
     * Returns an ArrayList which contains different tasks.
     * @return an ArrayList<Task>
     */
    public ArrayList<Task> getList() {
        return this.taskArray;
    }
}
