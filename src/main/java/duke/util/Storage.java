package duke.util;

import duke.task.Task;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class abstracts the load and store operations onto the disk.
 */
public class Storage {
    private String filepath;

    /**
     * A constructor for the Storage class.
     * @param filepath the relative filepath to store and load tasks from.
     */
    public Storage(String filepath){
        this.filepath = filepath;
    }

    /**
     * Loads the tasks from disk.
     * @return ArrayList <Tasks> which contain the tasks that have been saved onto the disk.
     */
    public ArrayList<Task> load() {
        File saveFile = new File(this.filepath);
        saveFile.getParentFile().mkdirs();
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.exit(1);
        }

        ArrayList<Task> taskList = new ArrayList<>(100);
        Scanner readFile = null;
        String nextSavedTask = null;
        try {
            readFile = new Scanner(saveFile).useDelimiter("\\|");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(readFile.hasNextLine()) {
            nextSavedTask = readFile.nextLine();
            taskList.add(Parser.parseFile(nextSavedTask));
        }
        readFile.close();
        return taskList;
    }

    /**
     * Adds a new task onto storage.
     * @param addedTask the task to be added onto storage.
     */
    public void saveAddedTask(Task addedTask) {
        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            fw.write(addedTask.toString());
            fw.close();
        } catch (IOException e) {
            System.exit(1);
        }
    }

    /**
     * Replaces a task in storage with the given task.
     * @param index the index of the stored task to be replaced.
     * @param updatedTask the updated task.
     */
    public void saveUpdatedTask(int index, Task updatedTask) {
        String tempFilePath = "data/temp.txt";
        File tempFile = new File(tempFilePath);
        File saveFile = new File(filepath);
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(saveFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String currentLine;
        int i = 0;
        try {
            while((currentLine = reader.readLine()) != null) {
                if (i == index) {
                    if (updatedTask != null) {
                        writer.write(updatedTask.toString());
                    }
                } else {
                    writer.write(currentLine + "\n");
                }
                i++;
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            Files.copy(Paths.get(tempFilePath),Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
