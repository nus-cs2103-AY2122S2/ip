import java.lang.*;
import java.util.*;
import java.io.Serializable;

public class Task implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;

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