import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    Storage(String filePath) throws DukeException {
        try {
            this.filePath = filePath;
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void write(String content) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public Scanner read() throws DukeException {
        try {
            File file = new File(filePath);
            return new Scanner(file);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void clear() throws DukeException {
        this.write("");
    }

    public void append(String content) throws DukeException{
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
