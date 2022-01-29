package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private File f;
    private Scanner s;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    public ArrayList<String> load() {
        ArrayList<String> tasksRead = new ArrayList<>();
        try {
            File taskFile = new File(filePath);
            if (taskFile.exists()) {
                this.s = new Scanner(f);
                while (s.hasNext()) {
                    tasksRead.add(s.nextLine());
                }
            } else {
                taskFile.createNewFile();
                this.s = new Scanner(f);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return tasksRead;
    }

    public void write(ArrayList<Task> textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task t: textToAdd) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }
}
