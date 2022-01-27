package duke.task;

import duke.storage.Storage;
import java.util.ArrayList;

public class Task {
    protected String name;
    protected boolean isDone;
    protected int id;
    private static ArrayList<Task> listOfTask = new ArrayList<>(100);
    private static int counter = 0;

    Task(String name) {
        this.name = name;
        this.id = counter + 1;
        this.isDone = false;
        listOfTask.add(this);
        counter++;
    }

    /**
     * Mark this Task object as done.
     */
    public void markDone() {
        this.isDone = true;
        updateFile();
    }

    /**
     * Mark this Task object as not done.
     */
    public void markNotDone() {
        this.isDone = false;
        updateFile();
    }

    /**
     * Delete this Task from the list.
     *
     * @param t Task object to be deleted
     */
    public void deleteTask(Task t) {
        listOfTask.remove(t);
        counter--;
        updateFile();
    }

    /**
     * Retrieve the current task list.
     *
     * @return a Task array consisting of the current task list.
     */
    public static Task[] getTaskList() {
        Task[] newArray = listOfTask.toArray(new Task[0]);
        return newArray;
    }

    /**
     * Retrieve the number of tasks that is currently present.
     *
     * @return a String consisting the number of tasks.
     */
    public static String getCounter() {
        return Integer.toString(counter);
    }

    /**
     * Retrieve a formatted String of the current task list.
     *
     * @return a String with the current task list
     */
    public static String printArray() {
        int arrayLength = listOfTask.size();
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

    private void updateFile() {
        Storage storage = new Storage(Storage.filePath);
        storage.writeToPath(Task.printArray());
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.name;
    }

}
