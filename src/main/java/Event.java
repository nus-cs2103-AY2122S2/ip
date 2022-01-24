import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {

    protected String at;

    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String message() {
        return "E | " + super.message() + "(at:" + at + ")";
    }

    @Override
    public void updateData(String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write( "\n" + this.message());
        fw.close();
    }
}
