import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DukeFile {
    private String path;

    public DukeFile(String path) {
        // Splits pathname into relative path and filename
        // Creates missing dir/ file
        this.path = path;
        String filename = path.substring(path.lastIndexOf("/") + 1);
        String dir = path.split(filename)[0];
        File d = new File(dir);
        File f = new File(path);
        if (!d.exists()) {
            d.mkdirs();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    public void updateTasks(ArrayList<Task> tasks) throws IOException {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        FileWriter log = new FileWriter(path);
        if (tasks.isEmpty()) {
            log.write("");
        } else {
            int size = tasks.size();
            for (int i = 0; i < size; i ++) {
                Task curr = tasks.get(i);
                String toWrite = curr.getType() + " | " + curr.getDone() + " | " + curr.getName();
                if (curr.getType() == "E" || curr.getType() == "D") {
                    toWrite += " | " + curr.getDead();
                }
                toWrite += "\n";
                log.write(toWrite);
            }
        }
        log.close();
    }

    public ArrayList<Task> readTasks() {
        File f = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        try {
            Scanner fileToRead = new Scanner(f);
            while (fileToRead.hasNext()) {
                String curr = fileToRead.nextLine();
                // taskComponent[0]: Type of Task
                // taskComponent[1]: Completion of Task, can be either "1" or "0"
                // taskComponent[2]: Name of Task
                // taskComponent[3]: Time of Task
                // \\| needed to split "|"
                String[] taskComponent = curr.split(" \\| ");
                switch (taskComponent[0]) {
                case "T":
                    ToDo newToDo= new ToDo(taskComponent[2]);
                    if (taskComponent[1].equals("1")) {
                        newToDo.mark();
                    }
                    tasks.add(newToDo);
                    break;
                case "D":
                    LocalDateTime d1 = LocalDateTime.parse(taskComponent[3], inputFormatter);
                    Deadline newDeadline = new Deadline(taskComponent[2], d1);
                    if (taskComponent[1].equals("1")) {
                        newDeadline.mark();
                    }
                    tasks.add(newDeadline);
                    break;
                case "E":
                    LocalDateTime d2 = LocalDateTime.parse(taskComponent[3], inputFormatter);
                    Event newEvent = new Event(taskComponent[2], d2);
                    if (taskComponent[1].equals("1")) {
                        newEvent.mark();
                    }
                    tasks.add(newEvent);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            return tasks;
        }
    }
}
