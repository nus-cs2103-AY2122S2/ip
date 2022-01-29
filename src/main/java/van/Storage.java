package van;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Storage {
    private ArrayList<String> tasks;
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
        tasks = new ArrayList<>();
        try {
            if (file.isFile()) {
                Scanner fileInput = new Scanner(this.file);
                while (fileInput.hasNextLine()) {
                    this.tasks.add(fileInput.nextLine());
                }
            } else {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Invalid file path. " + e);
        }
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void saveTasks(ArrayList<Task> newTasks) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (int i = 0; i < newTasks.size(); i++) {
                writer.print(newTasks.get(i).saveStatus() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
