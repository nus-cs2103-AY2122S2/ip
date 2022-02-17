package duke.storage;

import duke.ui.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

import java.util.Scanner;

public class Storage {

    private TaskList taskList;

    final String symbolForMarked = "[1]";
    final String symbolForUnMarked = "[0]";
    final String symbolForTask = "[T]";
    final String symbolForDeadline = "[D]";
    final String symbolForEvent = "[E]";
    final String FILEPATH = "data/data.txt";
    final String FILEDIRECTORY = "data";

    /**
     * Constructs a Storage object from data in data.txt file.
     *
     * @throws IOException Issue when writing or reading from data.txt
     */
    public Storage() throws IOException {
        try {
            File dataFile = new File(FILEPATH);
            Scanner sc = new Scanner(dataFile);
            TaskList newTaskList = new TaskList();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                String[] taskLineSplitBySpace = nextLine.split(" ");
                String taskType = taskLineSplitBySpace[0];
                String[] taskLineSplitBySlash = nextLine.split(" / ");

                newTaskList.add(convertToTask(taskType, taskLineSplitBySlash, taskLineSplitBySpace));
            }
            this.taskList = newTaskList;
        } catch (IOException e){
            Path filePath = Paths.get(FILEDIRECTORY);
            boolean dataDirectoryExists = Files.exists(filePath);
            if (!dataDirectoryExists) {
                new File(FILEDIRECTORY).mkdir();
            }
            new File(FILEPATH).createNewFile();
            this.taskList = new TaskList();
        } catch (DukeException e) {
            //Wrong format of tasks in data.txt
            System.out.println(e.getMessage());
        }
    }

    /**
     * Comprehends and converts input from data.txt into Task object
     *
     * @param taskType Type of Task: Deadline, Event or Todo
     * @param taskLineSplitBySlash Format of the line in a String array format split by slashes
     * @param taskLineSplitBySpace Format of the line in a String array format split by spaces
     * @return Task object to be put into TaskList
     * @throws DukeException If tasks are stored in wrong format in data.txt
     */
    public Task convertToTask(String taskType, String[] taskLineSplitBySlash, String[] taskLineSplitBySpace) throws DukeException {

        String wrongFormatError = "Tasks stored in wrong format";

        switch (taskType) {
        case symbolForTask:
            Todo newTodo = new Todo(taskLineSplitBySlash[1]);
            if (taskLineSplitBySpace[1].equals(symbolForMarked)) {
                newTodo.setMarkedTask();
            }
            return newTodo;

        case symbolForDeadline:
            //Checks whether there is a time component for the stored Deadline
            Deadline newDeadline = (taskLineSplitBySlash[3].equals("null"))
                    ? new Deadline(taskLineSplitBySlash[1], taskLineSplitBySlash[2])
                    : new Deadline(taskLineSplitBySlash[1], taskLineSplitBySlash[2], taskLineSplitBySlash[3]);

            if (taskLineSplitBySpace[1].equals(symbolForMarked)) {
                newDeadline.setMarkedTask();
            }
            return newDeadline;

        case symbolForEvent:
            //Checks whether there is a time component for the stored Event
            Event newEvent = (taskLineSplitBySlash[3].equals("null"))
                    ? new Event(taskLineSplitBySlash[1], taskLineSplitBySlash[2])
                    : new Event(taskLineSplitBySlash[1], taskLineSplitBySlash[2], taskLineSplitBySlash[3]);

            if (taskLineSplitBySpace[1].equals(symbolForMarked)) {
                newEvent.setMarkedTask();
            }
            return newEvent;

        default:
            throw new DukeException(wrongFormatError);
        }
    }

    /**
     * Converts task to string format for storage in the data.txt file for writing into the txt file
     *
     * @param task Task to be converted and written
     * @return String format of the task eg: [D] [1] / deadline / duedate / duetime
     */
    public String taskToStringConverter(Task task) {
        String output = "";
        if (task instanceof Todo) {
            String mark = (task.hasBeenMarked()) ? symbolForMarked : symbolForUnMarked;
            output = symbolForTask + " "  + mark + " / " + task.name + "\n";
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String mark = (deadline.hasBeenMarked()) ? symbolForMarked : symbolForUnMarked;
            output = symbolForDeadline + " " + mark + " / " + deadline.name + " / " + deadline.dueDate + " / "
                    + deadline.dueTime + "\n";
        } else if (task instanceof Event) {
            Event event = (Event) task;
            String mark = (event.hasBeenMarked()) ? symbolForMarked : symbolForUnMarked;
            output = symbolForEvent + " " + mark + " / " + event.name + " / " + event.dueDate + " / " + event.dueTime + "\n";
        }
        return output;
    }

    /**
     * Rewrites entire storage based on current taskList
     *
     * @throws IOException If there is an error with writing to data.txt
     */
    public void rewriteData() throws IOException {
        FileWriter fw = new FileWriter(this.FILEPATH);
        for (int i = 0; i < this.taskListSize(); i++) {
            Task task = this.taskList.get(i);
            fw.write(taskToStringConverter(task));
        }
        fw.close();
    }

    /**
     * Appends a single task to the file
     *
     * @param task task to be added to the data.txt file
     * @throws IOException if there is an error appending the task to data.txt
     */
    public void writeData(Task task) throws IOException {
        this.taskList.add(task);
        FileWriter fw = new FileWriter(this.FILEPATH, true);
        System.out.println(new File(this.FILEPATH).getAbsolutePath());
        fw.write(taskToStringConverter(task));
        fw.close();
    }

    /**
     * Deletes the entire file and rewrites it based on the new taskList.
     * Amends the current stored TaskList as well.
     *
     * @param idx index of task to be deleted
     * @throws IOException if there is an error rewriting data.txt
     */
    public void deleteData(int idx) throws IOException {
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
     *
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
        if (listOfTasks.equals("")) {
            return "You currently have no tasks. Yay! :)";
        }
        return listOfTasks;
    }

    /**
     * Gets task from Storage
     *
     * @param idx index of task to be gotten
     * @return task that is requested
     */
    public Task get(int idx) {
        return this.taskList.get(idx);
    }

    /**
     * Size of TaskList
     *
     * @return size of task list
     */
    public int taskListSize() {
        return this.taskList.size();
    }

    /**
     * Allows external access to TaskList
     *
     * @return TaskList
     */
    public TaskList accessTaskList() {
        return this.taskList;
    }

}
