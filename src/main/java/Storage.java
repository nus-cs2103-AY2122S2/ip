package duke.storage;
import duke.duke.Duke;
import duke.ui.Parser;
import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

public class Storage {
    TaskList taskList;
    String FILEPATH = "data/data.txt";

    /**
     * Constructs a Storage object. Loads the data from data/data.txt. if no data dir or data.txt is found, create an empty one
     */
    public Storage() throws IOException {
        try {
            File dataFile = new File(FILEPATH);
            Scanner sc = new Scanner(dataFile);
            TaskList newTaskList = new TaskList();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                String[] taskListaySplitBySpaces = nextLine.split(" ");
                String taskType = taskListaySplitBySpaces[0];
                String[] taskListaySplitBySlash = nextLine.split(" / ");
                switch (taskType) {
                case "[T]":
                    Todo newTodo = new Todo(taskListaySplitBySlash[1]);
                    if (taskListaySplitBySpaces[1].equals("[✓]")) {
                        newTodo.setMarkedTask();
                    }
                    newTaskList.add(newTodo);
                    break;
                case "[D]":
                    Deadline newDeadline = (taskListaySplitBySlash[3].equals("null")) ? new Deadline(taskListaySplitBySlash[1], taskListaySplitBySlash[2]) : new Deadline(taskListaySplitBySlash[1], taskListaySplitBySlash[2], taskListaySplitBySlash[3]);
                    if (taskListaySplitBySpaces[1].equals("[✓]")) {
                        newDeadline.setMarkedTask();
                    }
                    newTaskList.add(newDeadline);
                    break;
                case "[E]":
                    Event newEvent = (taskListaySplitBySlash[3].equals("null")) ? new Event(taskListaySplitBySlash[1], taskListaySplitBySlash[2]) : new Event(taskListaySplitBySlash[1], taskListaySplitBySlash[2], taskListaySplitBySlash[3]);
                    if (taskListaySplitBySpaces[1].equals("[✓]")) {
                        newEvent.setMarkedTask();
                    }
                    newTaskList.add(newEvent);
                    break;
                default:
                }
            }
            this.taskList = newTaskList;
        } catch (IOException e){
            Path filePath = Paths.get("data");
            boolean dataDirectoryExists = Files.exists(filePath);
            if (!dataDirectoryExists) {
                new File("data").mkdir();
            }
            new File(FILEPATH).createNewFile();
            this.taskList = new TaskList();
        }
    }

    /**
     *
     * @param task Converts task to string format for storage in the data.txt file for records
     * @return String format of the task eg: [D] [✓] deadline | duedate | duetime
     */
    public String taskToStringConverter(Task task) {
        String output = "";
        if (task instanceof Todo) {
            String mark = (task.hasBeenMarked()) ? "[✓]" : "[X]";
            output = "[T] " + mark + " / " + task.name + "\n";
        } else if (task instanceof Deadline deadline) {
            String mark = (deadline.hasBeenMarked()) ? "[✓]" : "[X]";
            output = "[D] " + mark + " / " + deadline.name + " / " + deadline.dueDate + " / " + deadline.dueTime + "\n";
        } else if (task instanceof Event event) {
            String mark = (event.hasBeenMarked()) ? "[✓]" : "[X]";
            output = "[E] " + mark + " / " + event.name + " / " + event.dueDate + " / " + event.dueTime + "\n";
        }
        return output;
    }

    /**
     * Appends a single task to the file
     * @param task task to be added to the data.txt file
     * @throws IOException if there is an error appending the task to data.txt
     */
    public void writeData(Task task) throws IOException {
        this.taskList.add(task);
        FileWriter fw = new FileWriter(this.FILEPATH, true);
        fw.write(taskToStringConverter(task));
        fw.close();
    }

    /**
     * Used when delete [index] is called for Duke. Deletes the entire file and rewrites it based on the new taskListay
     * Amends the current stored taskListay as well
     * @param idx index of task to be deleted
     * @throws IOException if there is an error rewriting data.txt
     */
    public void deleteData(int idx) throws IOException {
        Task taskToBeDeleted = this.taskList.get(idx);
        taskList.remove(idx);
        FileWriter fw = new FileWriter(this.FILEPATH);
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            fw.write(taskToStringConverter(task));
        }
        fw.close();
    }

    /**
     * Obtains list of tasks from this.taskList and returns it
     * @return String that lists out the tasks currently
     */
    public String list() {
        String listOfTasks = "";
        for (int i = 1; i <= this.taskList.size(); i++) {
            Task task = this.taskList.get(i - 1);
            if (task.hasBeenMarked()) {
                listOfTasks += i + ". " + task + "\n";
            } else {
                listOfTasks += i + ". " + task + "\n";
            }
        }
        return listOfTasks;
    }

    /**
     * Get task
     * @param idx index of task to be gotten
     * @return task that is requested
     */
    public Task get(int idx) {
        return this.taskList.get(idx);
    }

    /**
     * Size of tasklist
     * @return size of task list
     */
    public int taskListSize() {
        return this.taskList.size();
    }

}
