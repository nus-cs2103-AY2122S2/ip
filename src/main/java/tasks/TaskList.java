package tasks;

import exceptions.NoSuchTaskException;
import exceptions.SaveFileModifiedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * This class encapsulates a taskList.
 *
 * @author Ong Han Yang
 */
public class TaskList {
    /** The container for the items, implemented as an ArrayList*/
    private ArrayList<Task> list;

    /** Name of the saved file */
    private static String fileName = "saved-taskList.txt";

    /** Folder path to the saved file*/
    private static String filePath = "data";

    /**
     * Constructs a taskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds an item into the taskList.
     *
     * @param item the item to be added.
     */
    public void add(Task item) {
        this.list.add(item);
        this.saveToFile();
    }

    /**
     * Adds multiple items at a time to the taskList.
     *
     * @param items all the items to be added.
     */
    public void add(Task... items) {
        this.list.addAll(List.of(items));
        this.saveToFile();
    }

    /**
     * Removes an item from the list of tasks.
     *
     * @param taskIndex the index of the item to remove
     * @return the removed task.
     */
    public Task delete(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= this.list.size() || taskIndex < 0) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        Task output = this.list.remove(taskIndex);
        this.saveToFile();
        return output;
    }

    /**
     * Gets the length of the task list.
     *
     * @return an integer representing the length of the list.
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Marks a task as done or undone.
     *
     * @param taskIndex the index of the task to be marked.
     * @param isDone whether the task is to be done or not.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public void markTask(int taskIndex, boolean isDone) throws NoSuchTaskException {
        if (taskIndex >= this.list.size() || taskIndex < 0) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        this.list.get(taskIndex).markAs(isDone);
        this.saveToFile();
    }

    /**
     * Represents a stored task as a String.
     *
     * @param taskIndex the index of the task to be displayed.
     * @return String representation of the task.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public String displayTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= this.list.size() || taskIndex < 0) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        return this.list.get(taskIndex).toString();
    }

    /**
     * Displays the taskList as a String.
     *
     * @return String representation of the tasks.TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                // For the last item, don't append a newline character.
                sb.append(String.format("%d. %s", i + 1, list.get(i).toString()));
            } else {
                sb.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
            }
        }
        return sb.toString();
    }

    /**
     * Saves the taskList to the designated file at the filePath and fileName.
     */
    private void saveToFile() {

        try {
            String combinedFilePath = filePath + "/" + fileName;
            // creates the file if not there
            File f = new File(filePath);
            f.mkdir();
            File file = new File(combinedFilePath);
            file.createNewFile();
            // writes to the file the entirety of the toString().
            FileWriter fileWriter = new FileWriter(combinedFilePath);
            fileWriter.write(this.toString());
            fileWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads from the designated file to obtain a TaskList
     * that is identical to one that produced the file.
     *
     * @return an identical TaskList to one that produced the saved file.
     * @throws SaveFileModifiedException when the save file contains invalid
     *                                   symbols that could not have been from
     *                                   the string representation of a task,
     *                                   indicating external modification to
     *                                   the file.
     */
    public static TaskList loadFromFile() throws SaveFileModifiedException {
        TaskList output = new TaskList();
        try {
            File file = new File(filePath + "/" + fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = TaskList.parseLine(data);
                output.list.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // do nothing
        }
        return output;
    }



    /**
     * Parses a String representation of a task to get back the original Task's
     * details.
     *
     *  1. [T][X] sample task
     *  2. [D][ ] task (by: end of year)
     *  3. [E][ ] event (at: 9 Aug)
     *
     * @param input the String form of the Task.
     * @return the Task that corresponds to the input String
     * @throws SaveFileModifiedException when the save file contains invalid
     *                                   symbols that could not have been from
     *                                   the string representation of a task,
     *                                   indicating external modification to
     *                                   the file.
     */
    private static Task parseLine(String input) throws SaveFileModifiedException {
        Task output = null;
        String desc = null;
        int indexAftNumber = input.indexOf("[");
        int indexAftDesc = input.lastIndexOf("(");

        Supplier<String> timingSupplier = () -> input.substring(indexAftDesc + 5
                , input.length() - 1);
        Supplier<String> descSupplier = () -> input.substring(indexAftNumber + 7
                , indexAftDesc - 1);
        switch (input.charAt(indexAftNumber + 1)) {
        case 'T':
            desc = input.substring(indexAftNumber + 7);
            output = ToDoTask.of(desc);
            break;
        case 'D':
            output = DeadlineTask.of(descSupplier.get(), timingSupplier.get());
            break;
        case 'E':
            output = EventTask.of(descSupplier.get(), timingSupplier.get());
            break;
        default:
            throw new SaveFileModifiedException("Unknown task type detected!");
        }
        switch (input.charAt(indexAftNumber + 4)) {
        case 'X':
            output.markAs(true);
            break;
        case ' ':
            output.markAs(false);
            break;
        default:
            throw new SaveFileModifiedException("Unknown task state detected!");
        }
        return output;
    }
}
