package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Handles saving to file and loading the file to the task list.
 */
public class Storage {
    private static final String FILE_PATH = "./data/test.txt";

    /**
     * Saves the current list state to the statically set file.
     *
     * @param taskList Current task list
     * @return 1 if operation if successful
     */
    public static int saveToFile(List<Task> taskList) {
        FileWriter out = null;
        //Check if folder/file is present, otherwise create file
        File file = new File(FILE_PATH);
        try {
            String[] split = FILE_PATH.split("/");
            String directoryPath = FILE_PATH.substring(0, FILE_PATH.length()
                    - split[split.length - 1].length());
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileWriter(file);
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                String outputLine = currentTask.toOutputLine() + "\n";
                out.write(outputLine);
            }
            out.close();
        } catch (IOException exception) {
            return -1;
        }
        return 1;
    }

    /**
     * Checks the current file and loads entries into a list.
     *
     * @param path File path string
     * @return List of tasks
     */
    public static List<Task> loadFromFile(String path) {
        FileReader in = null;
        BufferedReader reader = null;
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            in = new FileReader(path);
            reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" \\| ");
                Task newTask = null;
                boolean marked = split[1].equals("1");
                if (split[0].equals("T")) {
                    newTask = new TodoTask(split[2], marked);
                } else if (split[0].equals("D")) {
                    if (split.length == 4) {
                        newTask = new DeadlineTask(split[2], marked, split[3]);
                    } else if (split.length == 5) {
                        newTask = new DeadlineTask(split[2], marked, split[3], split[4]);
                    }
                } else if (split[0].equals("E")) {
                    if (split.length == 4) {
                        newTask = new EventTask(split[2], marked, split[3]);
                    } else if (split.length == 5) {
                        newTask = new EventTask(split[2], marked, split[3], split[4]);
                    }
                }
                taskList.add(newTask);
            }
            in.close();
            reader.close();
        } catch (FileNotFoundException e) {
            return taskList;
        } catch (IOException e) {
            return taskList;
        } catch (IndexOutOfBoundsException e) {
            //in case somehow the .txt file format is broken
            return taskList;
        }
        return taskList;
    }
}
