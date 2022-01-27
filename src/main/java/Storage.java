import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private File file;
    private String filePath;
    private File directory;

    public Storage(String filePath) {
        file = new File("data/duke.txt");
        this.filePath = filePath;
        directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void printFileContent(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public void writeToFile(String filePath, List taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.toString());
        fw.close();
    }

    public ArrayList<Task> load() {

        ArrayList<Task> arrayList = new ArrayList<>();
        File file = new File("data/duke.txt");
        try {
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            while (sc.hasNext()) {
                line = sc.nextLine();
                Character c = line.charAt(4);
                if (c.equals('T')) {
                    String description = line.substring(10);
                    ToDo todo = new ToDo(description);
                    arrayList.add(todo);
                }
                if (c.equals('E')) {
                    int indexOfOpenBracket = line.indexOf('(');
                    int indexOfCloseBracket = line.indexOf(')');
                    String description = line.substring(10, indexOfOpenBracket - 1);
                    String time = line.substring(indexOfOpenBracket + 1, indexOfCloseBracket - 1);
                    Event event = new Event(description, time);
                    arrayList.add(event);
                }
                if (c.equals('D')) {
                    int indexOfOpenBracket = line.indexOf('(');
                    int indexOfCloseBracket = line.indexOf(')');
                    String description = line.substring(10, indexOfOpenBracket - 1);
                    String time = line.substring(indexOfOpenBracket + 1, indexOfCloseBracket - 1);
                    LocalDateTime dateTime = LocalDateTime.parse(time);
                    Deadline deadline = new Deadline(description, dateTime);
                    arrayList.add(deadline);
                }
            }
        } catch (FileNotFoundException e) {

        } finally {
            return arrayList;
        }
    }
}
