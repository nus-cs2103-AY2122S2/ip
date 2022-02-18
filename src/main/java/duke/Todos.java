package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * todo class
 * inherit from Task class
 */
public class Todos extends Task {
    private String frontDescription;
    private String backDescription;

    /**
     * @param description
     */
    public Todos(String description) {
        super(description);
        this.backDescription = description;
    }

    @Override
    public String frontDescription() {
        this.frontDescription = "T | " + "[" + this.getStatusIcon() + "] ";
        return frontDescription;
    }

    @Override
    public String backDescription() {
        return this.backDescription;
    }
    /**
     *
     * @return string to be printed
     */
    @Override
    public String message() {
        String message = frontDescription() + backDescription();
        return message;
    }

    @Override
    public String markDone(int currNo) {
        this.isDone = true;
        return this.message();
    }

    @Override
    public void updateData (String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(this.message() + "\n");
        fw.close();
    }

}
