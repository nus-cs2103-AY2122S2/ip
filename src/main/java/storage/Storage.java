package storage;

import exception.InvalidInputException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final String DEFAULT_FILEPATH = "/src/main/java/data.txt";

    public final String path;

    public Storage()  {
        this.path=getPath();
    }

    private String getPath() {
        String home = System.getProperty("user.dir");
        // works on *nix
        // works on Windows
        String path = home + DEFAULT_FILEPATH;
        boolean directoryExists = new java.io.File(path).exists();
        return path;
    }

    public List<Task> readTaskFile() throws IOException, InvalidInputException {

        List<Task> result = new ArrayList<>();
        String path = getPath();
        // Open the file
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
//            System.out.println("File not found under " + path);
            throw new FileNotFoundException("File not found under " + path);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        // Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            System.out.println(strLine);
            String[] parts = strLine.split("\\|");
            //  remove leading and trailing spaces
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            Parser parser = new Parser();
            if (parts[0].equals("T")) {
                result.add(new ToDo(parts[2], parts[1].equals("1")));
            } else if (parts[0].equals("D")) {
                result.add(new Deadline(parts[2], parts[1].equals("1"), parser.parseDate(parts[3])));
            } else if (parts[0].equals("E")) {
                result.add(new Event(parts[2], parts[1].equals("1"), parser.parseDate(parts[3])));
            } else {
                System.out.println(parts[0]);
            }
        }

        // Close the input stream
        fstream.close();
        return result;
    }


    public boolean saveTasktoFile(List<Task> taskList) throws FileNotFoundException {
        String path = getPath();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            FileWriter fw = new FileWriter(path);
            for (Task task : taskList) {
                System.out.println(task.getClass());
                if (task.getClass() == ToDo.class) {
                    fw.write("T | " + (task.getDone() ? "1 | " : "0 | ") + task.getTitle() + System.lineSeparator());
                } else if (task.getClass() == Deadline.class) {
                    fw.write("D | " + (task.getDone() ? "1 | " : "0 | ") + task.getTitle() + " | " + ((Deadline) task).getDate().format(format) + System.lineSeparator());
                } else if (task.getClass() == Event.class) {
                    fw.write("E | " + (task.getDone() ? "1 | " : "0 | ") + task.getTitle() + " | " +  ((Event) task).getDate().format(format) + System.lineSeparator());
                }
            }

            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException("File not found under " + path);
        }
        return true;
    }
}
