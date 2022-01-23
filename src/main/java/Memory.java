import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The Memory class abstracts the leading and saving of tasks given to Sana
 *
 * @author Jan
 * @version 1.0
 */
public class Memory {
    /**
     * This variable is the path where the taskList is saved
     */
    private final static String memPath = "data/sana.txt";

    /**
     * This variable is the path for directory that stores the memory file
     */
    private final static String dirPath = "data";

    /**
     * This variable represents the File object that stores the taskList
     */
    private File memFile;

    /**
     * The constructor for a Memory object
     */
    public Memory() {
        memFile = new File(memPath);
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdir();

        }
        if (!memFile.exists()) {
            try {
                memFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Converts the memory of tasks to a List of tasks
     *
     * @return  a List of tasks
     */
    public LinkedList<Task> memToList() {
        Scanner s = null;
        try {
            s = new Scanner(memFile);
        } catch (FileNotFoundException e) {
            System.out.println("I can't read the memory. Let's just start afresh!");
            return  new LinkedList<>();
        }
        LinkedList<Task> taskList = new LinkedList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            if (task.charAt(0) == 'T') { // a todo task
                taskList.add(ToDo.memToTask(task));
                /**
                try {

                } catch (MemoryCorruptedException e) {

                }
                 */
            } else if (task.charAt(0) == 'D') { // a Deadline task
                taskList.add(Deadline.memToTask(task));
            } else if (task.charAt(0) == 'E') { // an Event task
                taskList.add(Event.memToTask(task));
            }
        }
        s.close();
        return taskList;
    }

    /**
     * Updates the memory of tasks
     *
     * @param taskList  the list of tasks
     */
    public void updateMemory(LinkedList<Task> taskList) {
        FileWriter fw = new FileWriter(memPath);

    }


}
