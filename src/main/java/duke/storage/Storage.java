package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A class that saves and loads the tasks in a filepath.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Stores the TaskList in the specified filePath.
     *
     * @param filePath specified filePath.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
        if (!file.exists()) {
            createFile();
        }
    }

    /**
     * Creates a file, and if file is not in specified folder, creates the directory path toe the file too.
     */
    public void createFile() {
        try {
            File directory = new File(file.getParent());
            //data file not in specific folder, need to handle
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Saves tasks into file.
     *
     * @param tasks Tasks to be saved.
     * @throws IOException Exception if an I/O exception occurs.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            fileWriter.write(tasks.get(i).getTaskData() + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Loads up the tasks that were previously saved into Strings, and creates new file if required file
     * does not exist in computer.
     *
     * @return the Arraylist of Task objects.
     * @throws FileNotFoundException Exception if the input filepath is not found.
     * @throws IOException Exception if an I/O exception occurs.
     */
    public ArrayList<Task> load() throws FileNotFoundException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String task = sc.nextLine();

                char letter = getLetter(task);
                boolean isDone = getDoneStatus(task);
                // the content after letter and mark.
                String taskContent = getTaskContent(task);

                Task taskToAdd = generateTask(letter, isDone, taskContent);
                tasks.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            //required file doesnt exist in their computer, create new file
            this.file.createNewFile();
        }
        return tasks;
    }

    /**
     * Returns 'T', 'D', or 'E' based on Task type.
     * @param task the given task.
     * @return the letter of given task type.
     */
    public char getLetter(String task) {
        return task.charAt(0);
    }

    /**
     * Returns whether a task is done.
     *
     * @param task the given task.
     * @return the isDone status of task.
     */
    public boolean getDoneStatus(String task) {
        char markedBool = task.charAt(4);
        return markedBool == ('1');
    }

    /**
     * Returns the string of the task that was saved into formatted data.
     *
     * @param task the given task.
     * @return the formatted task string.
     */
    public String getTaskContent(String task) {
        return task.substring(8);
    }

    /**
     * Constructs the given task.
     *
     * @param letter char of task type.
     * @param isDone isDone status of task.
     * @param taskContent the formatted task string.
     * @return Task generated.
     */
    public Task generateTask(char letter, boolean isDone, String taskContent) {
        String[] tokens = taskContent.split("\\| ", 2);
        String description = tokens[0];
        switch (letter) {
        case 'T':
            return new Todo(description, isDone);
        case 'D':
            String by = tokens[1];
            return new Deadline(description, by, isDone);
        case 'E':
            String at = tokens[1];
            return new Event(description, at, isDone);
        default:
            assert false : "generateTask should not reach here.";
        }
        assert false : "generateTask should not reach here.";
        return null;
    }
}
