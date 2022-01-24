package duke;
import java.io.FileWriter;
import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task (String description) {
        this.description = description;
        this.isDone = false;

    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String markedDone() {
        this.isDone = true;
        return  this.message();

    }

    public String markedUndone() {
        this.isDone = false;
        return  this.message();
    }

    public String message() {
        return this.description;
    }

    public void updateData (String path) throws IOException {
        FileWriter fw = new FileWriter(path,true);
        fw.write( "\n" + this.getStatusIcon() + " | " + this.description);
        fw.close();
    }


}
