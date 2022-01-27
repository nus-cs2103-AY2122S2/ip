import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class LoadFile {
    public static boolean loaded = false;

    public static ArrayList<Task> load() throws FileNotFoundException {
        File file = new File("data/duke.txt");
        ArrayList<Task> list = new ArrayList<>();

        if (file.exists()) {
            loaded = true;
            try {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String str = s.nextLine();

                    Task task;

                    if (str.substring(1, 2).equals("T")) {
                        task = new Todo(str.substring(7));
                    }

                    else if (str.substring(1, 2).equals("E")) {
                        int startIndex = str.indexOf(" [at: ");
                        int endIndex = startIndex + 6;

                        task = new Event(str.substring(7, startIndex), LocalDate.parse(str.substring(endIndex, str.length() - 1)));
                    } else {
                        int startIndex = str.indexOf(" [by: ");
                        int endIndex = startIndex + 6;

                        task = new Deadline(str.substring(7, startIndex), LocalDate.parse(str.substring(endIndex, str.length() - 1)));
                    }

                    if (str.substring(4, 5).equals("X")) {
                        task.markAsDone();
                    }

                    list.add(task);

                }
            } catch (IOException e) {
                System.out.println("Oops, I wasn't able to load the previously saved list. :(");
            }
        }
        return list;
    }
}
