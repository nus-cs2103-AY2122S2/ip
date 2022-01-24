package duke;

import tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Storage implements StorageInterface{
    File f;

    public Storage(String filePath) {
        f = new File(filePath);
        if (!f.exists()){
            f = new File("Data");
            f.mkdirs();
            f = new File("Data/tasks.txt");
        }
    }

    public File load(){
        return f;
    }

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void save(TaskList taskList) throws IOException {
        try{
            Files.delete(Path.of("Data/tasks.txt"));
        } catch (NoSuchFileException e){}
        finally{
            for (Task t: taskList.getTasks()) {
                try {
                    writeToFile("Data/tasks.txt", t.saveFormat + "," + t.getStatus() + System.lineSeparator());
                } catch (FileNotFoundException e) {
                    File f = new File("Data");
                    f.mkdirs();
                    writeToFile("Data/tasks.txt", t.saveFormat + "," + t.getStatus() + System.lineSeparator());
                }
            }
        }
    }
}
