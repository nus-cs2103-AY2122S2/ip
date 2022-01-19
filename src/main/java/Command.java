import java.lang.*;
import java.util.*;

public class Command {
    int rank;
    String command;
    boolean isDone;

    public Command(int rank, String command) {
        this.rank = rank;
        this.command = command;
        this.isDone = false;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public String toString() {
        String isDone = this.isDone ? "[X] " : "[ ] ";
        return isDone + command;
    }
}