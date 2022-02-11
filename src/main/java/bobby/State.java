package bobby;

import java.util.ArrayList;

public class State {
    private ArrayList<Task> taskArray;
    // private int stateIndex;

    public State (ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
        // this.stateIndex = stateIndex;
    }

    public ArrayList<Task> loadState() {
        return this.taskArray;
    }

}
