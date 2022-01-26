import duke.DukeException;
import duke.Storage;

import java.util.Scanner;

public class StorageStub extends Storage {

    private String data = "";

    StorageStub(String filePath) throws DukeException {
        super(filePath);
    }

    public void write(String content) {
        data = content;
    }

    public Scanner read() {
        return new Scanner(data);
    }

    public void clear() {
        data = "";
    }

    public void append(String content) {
        data += content;
    }

}
