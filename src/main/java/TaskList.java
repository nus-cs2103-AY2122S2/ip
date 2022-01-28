import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

public class TaskList {

    private final ArrayList<Task> taskList;
    private final String TASK_ADDED = "\tTask Added, arrgh:\n";
    private static final String FILE_DIR = "src/main/data";
    private static final String FILE_PATH = "src/main/data/duke.txt";
    private int taskCount = 0;
    private Ui ui = new Ui();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(Scanner sc) throws FileNotFoundException, DukeException {
        this.taskList = new ArrayList<>();
        readFromFile(sc);
    }

    private void readFromFile(Scanner sc) throws DukeException {
        while (sc.hasNext()) {
            String[] savedData = sc.nextLine().split(" \\| ");
            String taskType = savedData[0];
            Task task;
            taskCount++;
            switch (taskType) {
            case "T":
                task = new Todo(savedData[2]);
                break;
            case "E":
                task = new Event(savedData[2], savedData[3]);
                break;
            case "D":
                task = new Deadline(savedData[2], savedData[3]);
                break;
            default:
                task = new Todo("");
            }
            if (savedData[1].equals("1")) {
                task.markAsDone();
            }
//            System.out.println("Task added.\n");
            taskList.add(task);
        }
    }

    /**
     * Adds a task into list of tasks.
     *
     * @param task List of tasks.
     */
    public void add(Task task) {
        if (task != null) {
            this.taskList.add(task);
            this.taskCount++;
            String contentToSave = task.toSave();
            Storage.append(contentToSave);
            System.out.println(TASK_ADDED + "\t" + taskList.get(this.taskCount - 1).toString());
            System.out.println("\tNow you have " + this.taskCount + " tasks in your task list arrr, better get workin' aye!\n");
            ui.requestNextCommand();
        } else {
            ui.showError("\tTask is invalid matey :-(, please try again!\n");
        }
    }

    /**
     * Deletes a task from list of tasks.
     *
     * @param taskIndex 0-based index task number to be deleted.
     */
    public void delete(int taskIndex) {
        try {
            Task task = taskList.get(taskIndex);
            this.taskList.remove(taskIndex);
            this.taskCount--;
            ui.deleteTask();
            System.out.println("\t" + task);
            ui.requestNextCommand();
            Storage.saveToFile(this.taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("\tAin't nuthin' to be deleted here matey! :-(\n");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getCount() {
        return this.taskCount;
    }

    public ArrayList<Task> getCurrentList() {
        return this.taskList;
    }

    /**
     * Returns the tasks at given index
     *
     * @param taskIndex 0-based index of task number.
     * @return Task at given index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    /**
     * Mark a task in list of tasks as done
     *
     * @param taskIndex 0-based index of task number.
     */
    public void markTask(int taskIndex) {
        Task curr = taskList.get(taskIndex);
        curr.markAsDone();
        Storage.saveToFile(this.taskList);
        ui.markAsDone();
        System.out.println("\t" + curr);
        ui.requestNextCommand();
    }

    /**
     * Mark a task in list of tasks as not done
     *
     * @param taskIndex 0-based index of task number.
     */
    public void unmarkTask(int taskIndex) {
        Task curr = taskList.get(taskIndex);
        curr.markAsUndone();
        Storage.saveToFile(this.taskList);
        ui.markAsUndone();
        System.out.println("\t" + curr);
        ui.requestNextCommand();
    }

    /**
     * Prints out the list of outstanding tasks
     */
    public void printTaskList() {
        ui.printTaskList();
        for (int i = 0; i < this.taskCount; i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.get(i).toString());
        }
        ui.requestNextCommand();
    }

//    /**
//     * Appends new tasks into saved file
//     *
//     * @param content Task to be added to saved file
//     */
//    private void append(String content) {
//        try {
//            FileWriter writer = new FileWriter(FILE_PATH, true);
//            writer.write(content);
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Exception thrown:" + e);
//        }
//    }
//
//    /**
//     * Save all tasks into a file
//     */
//    private void saveToFile() {
//        String result = "";
//        for (Task task : taskList) {
//            result = result.concat(task.toSave() + "\n");
//        }
//
//        try {
//            FileWriter writer = new FileWriter(FILE_PATH, false);
//            writer.write(result);
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Exception thrown:" + e);
//        }
//    }
}
