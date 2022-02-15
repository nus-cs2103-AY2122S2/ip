import java.io.*;
import java.util.*;

public class Files {

    public static void CreateFile() {
        try {
            File file = new File("data/duke.txt");
            if (file.createNewFile()) {
                System.out.println("New File created\n");
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void saveTasks(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            for (int i = 0; i < list.size(); i++) {
                Task tsk = list.get(i);
                writer.write(tsk.infoString() + "\n");
                }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void readTasks(ArrayList<Task> list) {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] details = task.split("/");

                Task constructed = null;

                if (details[0].equals("T")) {
                    constructed = new ToDo(details[2]);
                } else if (details[0].equals("D")) {
                    constructed = new Deadline(details[2], details[3]);
                } else {
                    constructed = new Event(details[2], details[3]);
                }

                if (details[1].equals("1")) {
                    constructed.mark();
                }

                list.add(constructed);
            }
            sc.close();
            System.out.println("File Loaded\n");
        } catch (FileNotFoundException e) {
            CreateFile();
        }
    }
}