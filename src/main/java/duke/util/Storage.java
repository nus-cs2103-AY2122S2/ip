package duke.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * This class abstracts the load and store operations onto the disk.
 */
public class Storage {

    private final String filepath;

    /**
     * A constructor for the Storage class.
     * @param filepath the relative filepath to store and load tasks from.
     */
    public Storage(String filepath) {
        assert filepath != null;
        this.filepath = filepath;
    }

    /**
     * Loads the tasks from disk.
     * @return ArrayList which contain the tasks that have been saved onto the disk.
     */
    public ArrayList<Task> load() {
        File saveFile = new File(this.filepath);
        saveFile.getParentFile().mkdirs();
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Task> taskList = new ArrayList<>(100);
        Scanner readFile = null;
        String nextSavedTask;

        try {
            readFile = new Scanner(saveFile).useDelimiter("\\|");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert readFile != null;

        while (readFile.hasNextLine()) {
            nextSavedTask = readFile.nextLine();
            Task savedTask = Parser.parseFile(nextSavedTask);
            taskList.add(savedTask);
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
            e.printStackTrace();
        }
    }

    /**
     * Replaces a task in storage with the given task.
     * @param index the index of the stored task to be replaced.
     * @param updatedTask the updated task. If null then the task is to be deleted.
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert writer != null;

        changeLine(reader, writer, updatedTask, index);

        try {
            // Update saved file with temp file and delete temp file.
            Files.copy(Paths.get(tempFilePath), Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Changes the task at the given line number with the given task.
     * @param reader reader io
     * @param writer writer io
     * @param updatedTask the task to be updated in save file. If null then delete the task in save file.
     * @param lineNumber the line number of the task in the saved file to be updated.
     */
    private void changeLine(BufferedReader reader, BufferedWriter writer, Task updatedTask, int lineNumber) {
        try {
            String currentLine;
            int i = -1;
            while ((currentLine = reader.readLine()) != null) {
                i++;
                if (i == lineNumber && updatedTask != null) {
                    // Update the task at lineNumber
                    writer.write(updatedTask.toString());
                } else if (i != lineNumber) {
                    writer.write(currentLine + "\n");
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
