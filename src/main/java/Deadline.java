import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String message() {
        return "D | " + super.message() + "(by:" + by + ")";
    }

    @Override
    public void updateData(String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write("\n" + this.message());
        fw.close();
    }
}
