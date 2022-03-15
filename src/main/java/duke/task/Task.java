package duke.task;

import duke.storage.Storage;
import java.util.ArrayList;

public class Task {
    protected String name;
    protected boolean isDone;
    protected int id;
    private static ArrayList<Task> tasks = new ArrayList<>(100);
    private static int counter = 0;

    Task(String name) {
        this.name = name;
        this.id = counter + 1;
        this.isDone = false;
        tasks.add(this);
        counter++;
    }

    /**
     * Marks this Task object as done.
     */
    public void markDone() {
        this.isDone = true;
        updateFile();
    }

    /**
     * Marks this Task object as not done.
     */
    public void markNotDone() {
        this.isDone = false;
        updateFile();
    }

    /**
     * Deletes this Task from the list.
     *
     * @param t Task object to be deleted
     */
    public void deleteTask(Task t) {
        tasks.remove(t);
        counter--;
        updateFile();
    }

    /**
     * Retrieves the current task list.
     *
     * @return a Task array consisting of the current task list.
     */
    public static Task[] getTaskList() {
        Task[] newArray = tasks.toArray(new Task[0]);
        return newArray;
    }

    /**
     * Retrieves the number of tasks that is currently present.
     *
     * @return a String consisting the number of tasks.
     */
    public static String getCounter() {
        return Integer.toString(tasks.size());
    }

    /**
     * Returns an Integer array containing the task id of the tasks containing the keyword.
     * Works even if the keyword is entered partially
     *
     * @param keyword the keyword user want to find,
     * @return
     */
    public static Integer[] findTask(String keyword) {
        int arrayLength = tasks.size();
        Task[] tempArray = Task.getTaskList();

        ArrayList<Integer> tasksWithKeyword = new ArrayList<Integer>();

        for(int i = 0; i < arrayLength; i++) {
            if(tempArray[i].name.contains(keyword)) {
                tasksWithKeyword.add(i);
            }
        }

        Integer[] result = new Integer[tasksWithKeyword.size()];
        tasksWithKeyword.toArray(result);

        return result;
    }

    /**
     * Retrieves a formatted String of the current task list.
     *
     * @return a String with the current task list
     */
    public static String printTaskList() {
        int arrayLength = tasks.size();
        Task[] tempArray = Task.getTaskList();
        String output = "       This is the list of all tasks :D \n";

        for(int i = 0; i < arrayLength; i++) {
            if(tempArray[i] != null) {
                output += "       " + Integer.toString(i + 1) + "." + tempArray[i];
                if(i != arrayLength - 1) {
                    output += "\n";
                }
            }

        }

        return output;
    }

    /**
     * Retrieves a formatted String of the current filtered task list based on elements in given array.
     *
     * @param arrayOfIdToBePrinted an integer array containing task number to be printed
     * @return a String with the current task list
     */
    public static String printTaskList(Integer[] arrayOfIdToBePrinted) {
        int arrayLength = arrayOfIdToBePrinted.length;
        Task[] tempArray = Task.getTaskList();
        String output = "";

        for (int i = 0; i < arrayLength; i++) {
            output += "       " + Integer.toString(i + 1) + "." + tempArray[arrayOfIdToBePrinted[i]];
            if (i != arrayLength - 1) {
                output += "\n";
            }
        }

        return output;
    }

        private void updateFile() {
        Storage storage = new Storage(Storage.filePath);
        storage.writeToPath(Task.printTaskList());
    }

    /**
     * Check whether the command name is present
     *
     * @param input input command of user
     * @return True if the command contains valid name, false otherwise
     */
    public static boolean isInvalidWithMissingName (String input) {
        String[] splitString = input.split("/", 2);
        String[] instruction = splitString[0].split(" ", 2);

        return instruction.length == 1 || instruction[1].equals("");
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.name;
    }

}
