import java.lang.Boolean;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
      this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File file = new File("duke.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] data = taskString.split(",");
                String type = data[0];
                Boolean status = Boolean.parseBoolean(data[1]);
                String text = data[2];
                if (data[0].equals("T")) {
                    tasks.add(new Todo(data[2], data[1]))
                }
                else if (data[0].equals("D")) {
                    String date = data[3];
                    tasks.add(new Deadline(text, status, date));
                }
                else if (data[0].equals("E")) {
                    String date = data[3];
                    tasks.add(new Deadline(text, status, date));
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Something went wrong");
        }
        return tasks;
    }

}
