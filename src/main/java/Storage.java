import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void save(TaskList tasklist) {
        try {
            List<Task> ls = tasklist.getList();
            File f = new File(filepath);
            f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(filepath);
            for (Task t : ls) {
                fw.write(t.saveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> load() {
        List<Task> ls = new ArrayList<>();
        File directory = new File("../../../data/");
        if (!directory.exists()) directory.mkdir();
        try {
            File saved = new File(filepath);
            if (!saved.exists()) {
                saved.createNewFile();
            } else {
                Scanner scan = new Scanner(saved);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] splitted = line.split("\\|");
                    boolean completed = splitted[1].equals("1");
                    if (splitted[0].equals("D")) {
                        Deadline toAdd = new Deadline(splitted[2], splitted[3]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                    if (splitted[0].equals("T")) {
                        Todo toAdd = new Todo(splitted[2]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                    if (splitted[0].equals("E")) {
                        Event toAdd = new Event(splitted[2], splitted[3]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return ls;
    }
}
