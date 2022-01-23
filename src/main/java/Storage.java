import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;


public class Storage {
    private final Path pathName;
    private final File save;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Storage() {
        this.pathName = Paths.get("../data/Duke.txt");
        this.save = new File(pathName.toString());
        try {
            if (save.getParentFile().mkdir()) {
                save.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(new DukeException("Failed to create directory " + save.getParent()));
        }
    }

    public ArrayList<Task> loadList() {
        Decoder decoder = new Decoder();
        try {
            Scanner s = new Scanner(save);
            while (s.hasNextLine()) {
                tasks.add(decoder.decode(s.nextLine()));
            }
            s.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return tasks;
    }

    public void addTask(Task currentTask) {
        try {
            FileWriter fileWriter = new FileWriter(save, true);
            fileWriter.write(currentTask.toString() + "\n");
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println(new DukeException("Storage Error!"));
        }
    }

    public void updateTasks(ArrayList<Task> currentTasks) {
        try {
            FileWriter fileWriter = new FileWriter(save);
            String allItems = "";
            for (int i = 0; i < currentTasks.size(); i++) {
                allItems = allItems + currentTasks.get(i).toString() + "\n";
            }
            fileWriter.write(allItems);
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println(new DukeException("Storage Error!"));
        }
    }
}
