package duke;

import java.util.ArrayList;

import java.io.File;

import java.io.IOException;

/**
 * Represents the storage class which is responsible
 * to store different tasks into a specific file or 
 * to read different tasks from a specific file.
 */
class Storage {
    private ArrayList<Task> taskArray;
    private String path;
    private static String HOME = System.getProperty("user.home");

    Storage(String path) {
        this.taskArray = new ArrayList<Task>();
        this.path = path;
    }

    /**
     * Loads the different tasks from a specific file.
     * If the file does not exist at the given path,
     * a new file will then be created.
     */
    public File load() {
        FileClass fc = new FileClass(); //file class 
        String homePath = HOME + "/data";
        path = HOME + path;
        fc.createDirectory(homePath); //create directory first
        fc.createFile(path); //create a file in the /home/data/tasks.txt
        File file = new File(path);
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
                String firstInitial = tasks.getInitial() ; //first initial character
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
     */
    public ArrayList<Task> getList() {
        return this.taskArray;
    }
    



}
