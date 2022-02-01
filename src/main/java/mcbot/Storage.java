package mcbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import mcbot.task.Task;
import mcbot.task.ToDo;
import mcbot.task.Deadline;
import mcbot.task.Event;

import mcbot.exception.McBotException;

/**
 * Storage class to store and load all the tasks. 
 */
public class Storage {
    String frameLine = "==========================================";
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
        //Load data from filePath, create folder or file or both if missing
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                File folder = new File("./data");
                if (!folder.exists()) {
                    boolean isFolderCreated = folder.mkdir();
                    boolean isFileCreated = f.createNewFile();
                    if (isFolderCreated && isFileCreated) {
                        System.out.println(frameLine);
                        System.out.println("I'ave created a new folder and file for ya, " + filePath + " to save yer list");
                        System.out.println(frameLine);
                    }
                } else {
                    boolean isFileCreated = f.createNewFile();
                    if (isFileCreated) {
                        System.out.println(frameLine);
                        System.out.println("I'ave created a new file for ya, " + filePath + " to save yer list");
                        System.out.println(frameLine);
                    }
                }
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] str = s.nextLine().split(" \\| ");
                Task t;
                switch (str[0]) {
                case "T":
                    t = new ToDo(str[2]);
                    break;
                case "D":
                    LocalDate deadlineDate = LocalDate.parse(str[3]);
                    if (str.length == 5) {
                        LocalTime time = LocalTime.parse(str[4]);
                        t = new Deadline(str[2], deadlineDate, time);
                    } else {
                        t = new Deadline(str[2], deadlineDate);
                    }
                    break;
                case "E":
                    LocalDate eventDate = LocalDate.parse(str[3]);
                    if (str.length == 5) {
                        LocalTime time = LocalTime.parse(str[4]);
                        t = new Event(str[2], eventDate, time);
                    } else {
                        t = new Event(str[2], eventDate);
                    }
                    break;
                default:
                    throw new McBotException("I dont understand the words in the file");
                }
                if (str[1].equals("1")) {
                    t.markDone();
                }
                arrList.add(t);
            }
        } catch (IOException | McBotException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Data loaded is not format correctly, some data might be missing");
        }
        return  arrList;
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
}
