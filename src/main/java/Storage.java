import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Storage {
    private String path;

    public Storage(String path) throws DukeException {
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
            throw new DukeException("WHERE YOUR NOTEPAD RECRUIT? YOU BETTER GO FIND IT NOW!");
        }
    }

    public void updateTasks(TaskList taskList) throws IOException {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        FileWriter log = new FileWriter(path);
        if (taskList.isEmpty()) {
            log.write("");
        } else {
            int size = taskList.size();
            for (int i = 0; i < size; i ++) {
                Task curr = taskList.get(i);
                String toWrite = curr.getType() + " | " + curr.getDone() + " | " + curr.getName();
                if (curr.getType() == "D") {
                    toWrite += " | " + curr.getDate() + " | " + curr.getTime();
                } else if (curr.getType() == "E") {
                    toWrite += " | " + curr.getDate() + " | " + curr.getTimeFrom() + " " + curr.getTimeTo();
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
        try {
            Scanner fileToRead = new Scanner(f);
            while (fileToRead.hasNext()) {
                // For formatting date/time
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

                String curr = fileToRead.nextLine();
                // taskComponent[0]: Type of Task
                // taskComponent[1]: Completion of Task, can be either "1" or "0"
                // taskComponent[2]: Name of Task
                // taskComponent[3]: Date of Task
                // taskComponent[4]: Time of Task
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
                    LocalDate d1 = LocalDate.parse(taskComponent[3], dateFormatter);
                    LocalTime t1 = LocalTime.parse(taskComponent[4], timeFormatter);
                    Deadline newDeadline = new Deadline(taskComponent[2], d1, t1);
                    if (taskComponent[1].equals("1")) {
                        newDeadline.mark();
                    }
                    tasks.add(newDeadline);
                    break;
                case "E":
                    //dTA[0] = date
                    //dTA[1] = timeFrom
                    //dTA[2] = timeTo
                    String[] timeArray = taskComponent[4].split(" ");
                    LocalDate d2 = LocalDate.parse(taskComponent[3], dateFormatter);
                    LocalTime timeFrom = LocalTime.parse(timeArray[0], timeFormatter);
                    LocalTime timeTo = LocalTime.parse(timeArray[1], timeFormatter);
                    Event newEvent = new Event(taskComponent[2], d2, timeFrom, timeTo);
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
