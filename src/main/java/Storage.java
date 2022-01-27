import Tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String pathName;

    public Storage(String pathName) {
        this.pathName = pathName;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> ls = new ArrayList<Task>();
        File data = new File(pathName);
        try {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] arr = str.split("|");
                String type = arr[0];
                boolean marked = arr[2].equals("1");
                String remainingStr = str.substring(str.lastIndexOf("|") + 1);
                switch (type) {
                case "D":
                    ls.add(new DeadLines(
                            str.substring(4, str.lastIndexOf("|")),
                            marked, remainingStr));
                    break;
                case "E":
                    ls.add(new Events(str.substring(4, str.lastIndexOf("|")),
                            marked, remainingStr));
                    break;
                case "T":
                    ls.add(new ToDos(remainingStr, marked));
                    break;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no cache, " +
                    "duke will be initialised as per normal.");
        }
        return ls;
    }

    public void write(TaskList arr) {
        try {
            File f = new File(pathName);
            FileWriter fw = new FileWriter(f);
            for (Task t : arr) {
                fw.write(t.cacheString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
