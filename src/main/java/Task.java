import java.lang.*;
import java.util.*;

public class Task {

    int rank;
    String task;
    boolean isDone;

    public Task(int rank, String task) {
        this.rank = rank;
        this.isDone = false;
        this.task = task;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public String toString() {
        String isDone = this.isDone ? "[X] " : "[ ] ";
        return isDone + task;
    }
}