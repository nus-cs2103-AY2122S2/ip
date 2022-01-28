package saitama.tasks;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A to do.
 */
public class ToDo extends Task {

    /**
     * Initialises an undone to do task.
     *
     * @param description The details of the to do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initialises a to do task.
     *
     * @param description The details of the to do task.
     * @param isDone Whether the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file.
     * @throws IOException if there is an error writing the file.
     */
    public void saveTask(FileWriter fw) throws IOException {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        fw.write("T " + isDone + " " + this.description + "\n");
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}