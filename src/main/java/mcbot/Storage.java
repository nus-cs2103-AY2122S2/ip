package mcbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import mcbot.exception.McBotException;
import mcbot.task.Deadline;
import mcbot.task.Event;
import mcbot.task.Task;
import mcbot.task.ToDo;

/**
 * Storage class to store and load all the tasks. 
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for the storage. 
     * 
     * @param filePath is the path of the file to be read. 
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * To read the file and load each task into the task list.
     * 
     * @return Arraylist containing all the task from the file. 
     * @throws McBotException if there is an error reading the file. 
     */
    public ArrayList<Task> load() throws McBotException {
        ArrayList<Task> arrList = new ArrayList<>(100);
        try {
            File f = new File(filePath);
            checkFile(f);
            Scanner s = new Scanner(f);
            populateData(s, arrList);
        } catch (IOException | McBotException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Data loaded is not format correctly, some data might be missing");
        }
        return arrList;
    }

    /**
     * Method to check if both folder and file to save the data exists.
     * 
     * If either is not found, then create the folder or 
     * file for the user.
     * 
     * @param f is the File to be checked.
     * @throws IOException when file or folder cannot be created.
     */
    private void checkFile(File f) throws IOException {
        File folder = new File("./data");
        boolean isFileMissing = !f.exists();
        boolean isFolderMissing = !folder.exists();
        if (isFolderMissing) {
            boolean isFolderCreated = folder.mkdir();
            boolean isFileCreated = f.createNewFile();
            if (isFolderCreated && isFileCreated) {
                System.out.println("I'ave created a new folder and file for ya, " 
                        + filePath + " to save yer list");
            } else {
                System.out.println("Something went wrong in creating file/folder.");
            }
        } else if (isFileMissing) {
            boolean isFileCreated = f.createNewFile();
            if (isFileCreated) {
                System.out.println("I'ave created a new file for ya, " + filePath + " to save yer list");
            } else {
                System.out.println("Something went wrong in creating file.");
            }
        }
    }

    /**
     * Updates the data file with the updates task list.
     * Rewrites each task one by one. 
     * 
     * @param taskList The list containing the tasks.
     */
    public void updateData(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : taskList) {
                fw.write(t.toDataString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Appends a new task to the data file.
     * Writes a new line of task to the data file.
     * 
     * @param task The task to be saved into the data file.
     */
    public void appendData(Task task) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.toDataString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Helper method for load method.
     * 
     * @param s scanner used.
     * @param arrList is the array list used to save the tasks.
     * @throws McBotException if file data is unreadable.
     */
    private void populateData(Scanner s, ArrayList<Task> arrList) throws McBotException {
        while (s.hasNext()) {
            String[] str = s.nextLine().split(" \\| ");
            Task t = getTask(str);
            if (str[1].equals("1")) {
                t.markDone();
            }
            arrList.add(t);
        }
    }

    /**
     * Helper method for populateData method.
     * 
     * @param str is the String array of data.
     * @return the task created for each data line read.
     * @throws McBotException if file data is unreadable.
     */
    private Task getTask(String[] str) throws McBotException {
        switch (str[0]) {
        case "T":
            return new ToDo(str[2]);
        case "D":
            LocalDate deadlineDate = LocalDate.parse(str[3]);
            if (str.length == 5) {
                LocalTime time = LocalTime.parse(str[4]);
                return new Deadline(str[2], deadlineDate, time);
            } else {
                return new Deadline(str[2], deadlineDate);
            }
        case "E":
            LocalDate eventDate = LocalDate.parse(str[3]);
            if (str.length == 5) {
                LocalTime time = LocalTime.parse(str[4]);
                return new Event(str[2], eventDate, time);
            } else {
                return new Event(str[2], eventDate);
            }
        default:
            throw new McBotException("I dont understand the words in the file");
        }
    }
}
