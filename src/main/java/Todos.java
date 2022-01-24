import java.io.FileWriter;
import java.io.IOException;

public class Todos extends Task {

    public Todos(String description) {
        super(description);

    }

    @Override
    public String message() {
        return "T | " + super.message() ;
    }

    @Override
    public void updateData(String path) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write("\n" + this.message());
        fw.close();
    }


}
