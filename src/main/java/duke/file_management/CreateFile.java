package duke.file_management;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    private String fileName;

    public CreateFile() {
        this.fileName = "duke.txt";
    }

    public String getFileName() {
        return fileName;
    }

    public boolean createFile() {
        boolean bool = false;
        try {
            File file = new File(this.fileName);
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                // System.out.println("File has been created at: " + file.getCanonicalPath());
                bool = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }
}
