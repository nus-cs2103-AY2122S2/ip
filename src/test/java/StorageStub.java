import java.util.Scanner;

import tsohg.Storage;
import tsohg.TsohgException;

public class StorageStub extends Storage {

    private String data = "";

    StorageStub(String filePath) throws TsohgException {
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
