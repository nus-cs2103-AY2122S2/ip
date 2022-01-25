package sana;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.LinkedList;
import java.util.Scanner;

import sana.task.*;

/**
 * The Memory class abstracts the leading and saving of tasks given to Sana
 *
 * @author Jan
 * @version 1.0
 */
public class Memory {
    /** This variable is the path where the taskList is saved */
    private final static String memPath = "data/sana.txt";

    /** This variable is the path for directory that stores the memory file */
    private final static String dirPath = "data";

    /** This variable represents the File object that stores the taskList */
    private File memFile;

    /** The constructor for a Memory object */
    public Memory() {
        initialiseMem();
    }

    /** Create new sana.txt file and data dir in case of corruption */
    private void initialiseMem() {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        memFile = new File(memPath);
        if (!memFile.exists()) {
            try {
                memFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.memFile = new File(memPath);
    }

    /**
     * Converts the memory of tasks to a List of tasks
     *
     * @return  a List of tasks
     */
    public LinkedList<Task> memToList() {
        Scanner s;
        try {
            s = new Scanner(memFile);
        } catch (FileNotFoundException e) {
            // Shouldn't happen since memory is initialised during initialisation of Sana
            System.out.println("I can't read the memory. Let's just start afresh!");
            initialiseMem();
            return new LinkedList<>();
        }
        LinkedList<Task> taskList = new LinkedList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            if (task.charAt(0) == 'T') { // a todo sana.task
                taskList.add(ToDo.memToTask(task));
            } else if (task.charAt(0) == 'D') { // a Deadline sana.task
                taskList.add(Deadline.memToTask(task));
            } else if (task.charAt(0) == 'E') { // an Event sana.task
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
        try {
            FileWriter fw = new FileWriter(memFile);
            for (Task task: taskList) {
                fw.write(task.taskToMemStr() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("My memory was messed with while running, I'll reinitialise it :)");
            initialiseMem();
        }
    }


}
