package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * todo class
 * inherit from Task class
 */
public class Todos extends Task {

    /**
     * @param description
     */
    public Todos(String description) {
        super(description);

    }

    @Override
    public String frontDescription() {
        return "T | " + "[" + this.getStatusIcon() + "] ";
    }
    @Override
    public String backDescription() {
        return super.message();
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
