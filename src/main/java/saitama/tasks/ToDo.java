package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public void saveTask(FileWriter fw) throws IOException {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        fw.write("T " + isDone + " " + this.description + "\n");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}