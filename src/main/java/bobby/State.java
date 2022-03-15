package bobby;

import java.util.ArrayList;

/**
 * Represents a state of the program at any point in time. In current
 * implementation, State is represented by the arraylist of tasks.
 */
public class State {
    private ArrayList<Task> taskArray;

    public State (ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public ArrayList<Task> loadState() {
        ArrayList<Task> taskArrayCopy = new ArrayList<Task>(taskArray);
        return taskArrayCopy;
    }

}
