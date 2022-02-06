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
     * Loads up the tasks that were previously saved, and creates new file if required file
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
                char letter = task.charAt(0);
                char isMarked = task.charAt(4);
                boolean isDone = isMarked == ('1');
                String taskLine = task.substring(8);
                Task taskToAdd = null;
                switch (letter) {
                case 'T':
                    taskToAdd = new Todo(taskLine, isDone);
                    break;
                case 'D':
                    String[] theTask = taskLine.split(" \\| ", 2);
                    taskToAdd = new Deadline(theTask[0], theTask[1], isDone);
                    break;
                case 'E':
                    String[] eventTasking = taskLine.split(" \\| ", 2);
                    taskToAdd = new Event(eventTasking[0], eventTasking[1], isDone);
                    break;
                default:
                    //will not reach here.
                }
                tasks.add(taskToAdd);
            }
        } catch (FileNotFoundException e) { //required file doesnt exist in their computer, create new file
            this.file.createNewFile();
        }
        return tasks;
    }
}
