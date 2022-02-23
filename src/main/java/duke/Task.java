package duke;
import java.io.FileWriter;
import java.io.IOException;

/**
 * superclass of other task classes
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * @param description
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;

    }

    /**
     * determine if a task is done or undone
     * @return done or undone
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }
    public void setDone() {
         this.isDone = true;
    }
    /**
     * mark the task as undone
     * @return a message to verify that task is marked as undone
     */
    public String markedUndone() {
        this.isDone = false;
        return this.message();
    }

    public String message() {
        return this.description;
    }

    /**
     * to update the data into file
     * @param path
     * @throws IOException
     */
    public void updateData (String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(this.getStatusIcon() + " | " + this.description + "\n");
        fw.close();
    }

    public String getDescription () {
        return this.description;
    }
    public String frontDescription() {
        return "";
    }
    public String backDescription() {
        return this.description;
    }

    /**
     * @param currNo
     * @return
     */
    public String markDone(int currNo) {
        this.isDone = true;
        return this.message();
    }
}
