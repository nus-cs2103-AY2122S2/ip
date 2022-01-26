package duke;
import java.io.FileWriter;
import java.io.IOException;

/**
 * superclass of other task classes
 */
public class Task {
    protected String description;
    protected boolean isDone;


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

    /**
     * mark a task as done
     * @return a message to verify that task is marked as done
     */
    public String markedDone() {
        this.isDone = true;
        return  this.message();

    }

    /**
     * mark the task as undone
     * @return a message to verify that task is marked as undone
     */
    public String markedUndone() {
        this.isDone = false;
        return  this.message();
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
        FileWriter fw = new FileWriter(path,true);
        fw.write( "\n" + this.getStatusIcon() + " | " + this.description);
        fw.close();
    }


}
