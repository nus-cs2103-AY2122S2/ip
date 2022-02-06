package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a <code>Storage</code> object to handle saving and loading.
 */


public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves current tasklist as a file in specified filepath.
     * @param tasklist
     */
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

    /**
     * Loads contents of current saved file into tasklist.
     * @return The list containing the pre-saved tasks.
     */
    public List<Task> load() {
        List<Task> ls = new ArrayList<>();
        File directory = new File("../../../data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            File saved = new File(filepath);
            if (!saved.exists()) {
                saved.createNewFile();
            } else {
                Scanner scan = new Scanner(saved);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] splitted = line.split("\\|");
                    String firstToken = splitted[0];
                    String name = splitted[2];
                    boolean completed = splitted[1].equals("1");
                    if (firstToken.equals("D")) {
                        String timing = splitted[3];
                        Deadline toAdd = new Deadline(name, timing);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                    if (firstToken.equals("T")) {
                        Todo toAdd = new Todo(splitted[2]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                    if (firstToken.equals("E")) {
                        String timing = splitted[3];
                        Event toAdd = new Event(name, timing);
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
