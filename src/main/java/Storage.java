import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

public class Storage {
    ArrayList<Task> arr;
    String FILEPATH = "data/data.txt";

    /**
     * Constructs a Storage object. Loads the data from data/data.txt. if no data dir or data.txt is found, create an empty one
     */
    public Storage() throws IOException {
        try {
            File dataFile = new File(FILEPATH);
            Scanner sc = new Scanner(dataFile);
            ArrayList<Task> newArr = new ArrayList<>();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                String[] nextLineArr = nextLine.split(" ");
                String taskType = nextLineArr[0];
                switch (taskType) {
                case "[T]":
                    Todo newTodo = new Todo(nextLineArr[2]);
                    if (nextLineArr[1].equals("[✓]")) {
                        newTodo.setMarkedTask();
                    }
                    newArr.add(newTodo);
                    break;
                case "[D]":
                    Deadline newDeadline = (nextLineArr[6].equals("null")) ? new Deadline(nextLineArr[2], nextLineArr[4]) : new Deadline(nextLineArr[2], nextLineArr[4], nextLineArr[6]);
                    if (nextLineArr[1].equals("[✓]")) {
                        newDeadline.setMarkedTask();
                    }
                    newArr.add(newDeadline);
                    break;
                case "[E]":
                    Event newEvent = (nextLineArr[6].equals("null")) ? new Event(nextLineArr[2], nextLineArr[4]) : new Event(nextLineArr[2], nextLineArr[4], nextLineArr[6]);
                    if (nextLineArr[1].equals("[✓]")) {
                        newEvent.setMarkedTask();
                    }
                    newArr.add(newEvent);
                    break;
                default:
                }
            }
            this.arr = newArr;
        } catch (IOException e){
            Path filePath = Paths.get("data");
            boolean dataDirectoryExists = Files.exists(filePath);
            if (!dataDirectoryExists) {
                new File("data").mkdir();
            }
            new File(FILEPATH).createNewFile();
            this.arr = new ArrayList<>();
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
            output = "[T] " + mark + " " + task.name + "\n";
        } else if (task instanceof Deadline deadline) {
            String mark = (deadline.hasBeenMarked()) ? "[✓]" : "[X]";
            output = "[D] " + mark + " " + deadline.name + "| " + deadline.dueDate + " | " + deadline.dueTime + "\n";
        } else if (task instanceof Event event) {
            String mark = (event.hasBeenMarked()) ? "[✓]" : "[X]";
            output = "[E] " + mark + " " + event.name + "| " + event.dueDate + " | " + event.dueTime + "\n";
        }
        return output;
    }

    /**
     * Appends a single task to the file
     * @param task task to be added to the data.txt file
     * @throws IOException if there is an error appending the task to data.txt
     */
    public void writeData(Task task) throws IOException {
        this.arr.add(task);
        FileWriter fw = new FileWriter(this.FILEPATH, true);
        fw.write(taskToStringConverter(task));
        fw.close();
    }

    /**
     * Used when delete [index] is called for Duke. Deletes the entire file and rewrites it based on the new array
     * Amends the current stored array as well
     * @param idx index of task to be deleted
     * @throws IOException if there is an error rewriting data.txt
     */
    public void deleteData(int idx) throws IOException {
        Task taskToBeDeleted = this.arr.get(idx);
        arr.remove(idx);
        FileWriter fw = new FileWriter(this.FILEPATH);
        for (Task task : this.arr) {
            fw.write(taskToStringConverter(task));
        }
        fw.close();
    }

    /**
     * Obtains list of tasks from this.arr and returns it
     * @return String that lists out the tasks currently
     */
    public String list() {
        String listOfTasks = "";
        int i = 0;
        for (Task item : this.arr) {
            i += 1;
            if (item.hasBeenMarked()) {
                listOfTasks += i + ". " + item + "\n";
            } else {
                listOfTasks += i + ". " + item + "\n";
            }
        }
        return listOfTasks;
    }

    /**
     *
     * @param idx index of task to be gotten
     * @return task that is requested
     */
    public Task get(int idx) {
        return this.arr.get(idx);
    }

    /**
     *
     * @return size of task list
     */
    public int taskListSize() {
        return this.arr.size();
    }

}
