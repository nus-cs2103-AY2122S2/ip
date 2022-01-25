package main.java.mike;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public File loadFile() {
        File file = new File(filePath);
        return file;
    }

    void storeList(String listInStorageFormat) {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            fw.write(listInStorageFormat);
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
