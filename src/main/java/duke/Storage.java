package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


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
                    String time = line.substring(indexOfOpenBracket + 4, indexOfCloseBracket);
                    Event event = new Event(description, time);
                    arrayList.add(event);
                }
                if (c.equals('D')) {
                    int indexOfOpenBracket = line.indexOf('(');
                    int indexOfCloseBracket = line.indexOf(')');
                    String description = line.substring(10, indexOfOpenBracket - 1);
                    String time = line.substring(indexOfOpenBracket + 4, indexOfCloseBracket);
                    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(time, outputFormat);
                    Deadline deadline = new Deadline(description, dateTime);
                    arrayList.add(deadline);
                }
            }
        } catch (FileNotFoundException e) {

        }
        return arrayList;
    }
}
