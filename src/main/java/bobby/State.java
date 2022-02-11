package bobby;

import java.util.ArrayList;

public class State {
    private ArrayList<Task> taskArray;
    private int stateIndex;

    public State (ArrayList<Task> taskArray, int stateIndex) {
        this.taskArray = taskArray;
        this.stateIndex = stateIndex;
    }

    public int getStateIndex() {
        return stateIndex;
    }

    public ArrayList<Task> loadState() {
        ArrayList<Task> taskArrayCopy = new ArrayList<Task>(taskArray);
        return taskArrayCopy;
    }

}
