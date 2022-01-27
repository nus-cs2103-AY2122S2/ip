import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Commands {

    private final TaskHistory taskHistory = new TaskHistory();

    public Commands() { // Empty Constructor
    }

    public void bye() { // Get DukeLCH to Exit
        String bye = "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }

    public String convertToDukeDate(String date) {
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        df1.setLenient(false);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        df2.setLenient(false);
        try {
            Date dummyDate1 = df1.parse(date);
            LocalDate dukeDate1 = dummyDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return dukeDate1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (ParseException e1) {
            try {
                Date dummyDate2 = df2.parse(date);
                LocalDate dukeDate2 = LocalDate.parse(date);
                return dukeDate2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (ParseException e2) {
                return date;
            }
        }
    }

    public String convertToDukeTime(String time) {
        if (time.contains("am") || time.contains("pm")
                || time.contains("AM") || time.contains("PM")
                || time.contains("Am") || time.contains("Pm")) {
            return time;
        } else {
            String splicedTime = time.substring(0, 4);
            DateFormat inputFormat = new SimpleDateFormat("HHmm");
            DateFormat outputFormat = new SimpleDateFormat("hh:mmaa");
            try {
                Date dukeTime = inputFormat.parse(splicedTime);
                return outputFormat.format(dukeTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return time;
            }
        }
    }


    public void list() { // Get DukeLCH to List cmdHistory
        String border = "_______________________________________________________\n";
        System.out.println(border
                + "These are your tasks that we have in our records:\n"
                + taskHistory.printAll() + border);
    }

    public void mark(int index) {
        taskHistory.getTask(index).markTask();
        Task currTask = taskHistory.getTask(index);
        String tasking = "";
        if (currTask instanceof ToDos) {
            ToDos temp = (ToDos) currTask;
            tasking = tasking.concat(temp.getToDo());
        } else if (currTask instanceof Deadlines) {
            Deadlines temp = (Deadlines) currTask;
            tasking = tasking.concat(temp.getDeadline());
        } else if (currTask instanceof Event) {
            Event temp = (Event) currTask;
            tasking = tasking.concat(temp.getEvent());
        } else {
            System.out.println("Error occurred while processing " + currTask.getTask()); // Temporary error handler
        }
        String msg = "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + "    " + tasking
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    public void unmark(int index) {
        taskHistory.getTask(index).unmarkTask();
        Task currTask = taskHistory.getTask(index);
        String tasking = "";
        if (currTask instanceof ToDos) {
            ToDos temp = (ToDos) currTask;
            tasking = tasking.concat(temp.getToDo());
        } else if (currTask instanceof Deadlines) {
            Deadlines temp = (Deadlines) currTask;
            tasking = tasking.concat(temp.getDeadline());
        } else if (currTask instanceof Event) {
            Event temp = (Event) currTask;
            tasking = tasking.concat(temp.getEvent());
        } else {
            System.out.println("Error occurred while processing " + currTask.getTask()); // Temporary error handler
        }
        taskHistory.getTask(index).unmarkTask();
        String msg = "_______________________________________________________\n"
                + "A reminder that the following task has not been done:\n"
                + "    " + tasking
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    public void todo(String[] tokens) {
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            description = description.concat(tokens[i]);
            if (i != (tokens.length - 1)) {
                description = description.concat(" ");
            }
        }
        taskHistory.addToDo(description);
    }

    public void deadline(String[] tokens) throws DukeException {
        String description = "";
        String date = "";
        String time = "";
        int timeStart = -1; // -1 is a placeholder to indicate /by has not been found
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                timeStart = i;
                break;
            } else {
                description = description.concat(tokens[i]);
            }
            description = description.concat(" ");
        }
        // Check for Date and Time
        if (timeStart == -1) {
            throw new DukeException("'/by' not detected");
        }
        // Handle Date
        date = date.concat(convertToDukeDate(tokens[timeStart + 1]));
        // Handle Time
        time = time.concat(convertToDukeTime(tokens[timeStart + 2]));
        taskHistory.addDeadline(description, date, time);
    }

    public void event(String[] tokens) throws DukeException {
        String description = "";
        String date = "";
        String time = "";
        int timeStart = -1; // -1 is a placeholder to indicate /at has not been found
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                timeStart = i;
                break;
            } else {
                description = description.concat(tokens[i]);
            }
            description = description.concat(" ");
        }

        // Check for timeFrame
        if (timeStart == -1) {
            throw new DukeException("'/at' not detected");
        }
        // Handle Date
        date = date.concat(convertToDukeDate(tokens[timeStart + 1]));
        // Handle Time
        String[] arr = tokens[timeStart + 2].split("-");
        time = time.concat(convertToDukeTime(arr[0])
                .concat("-")
                .concat(convertToDukeTime(arr[1])));
        taskHistory.addEvent(description, date, time);
    }

    public void delete(int index) {
        taskHistory.deleteTask(index);
    }

    File startup() throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke", "data");
        String filePath = String.valueOf(path);
        // Check if parent and child directory exists else create them
        File f1 = new File(filePath);
        f1.mkdirs();
        // Check if duke.txt exits else create it
        File f2 = new File(filePath + "\\duke.txt");
        f2.createNewFile();

        return f2;
    }

    public void restore(File curr) throws FileNotFoundException {
        Scanner s = new Scanner(curr);
        while (s.hasNext()) {
            String temp = s.nextLine();
            String[] arr = temp.split(":");
            int mark = Integer.parseInt(arr[1]);
            switch (arr[0]) {
            case "T":
                taskHistory.addToDo(mark, arr[2]);
                break;
            case "D":
                taskHistory.addDeadline(mark, arr[2], arr[3]);
                break;
            case "E":
                taskHistory.addEvent(mark, arr[2], arr[3]);
                break;
            default:
                System.out.println("load unsuccessful");
                break;
            }
        }
    }

    void update(File curr) throws IOException {
        String content = taskHistory.formatRecord();
        FileWriter fw = new FileWriter(curr);
        fw.append(content);
        fw.flush();
        fw.close();
    }
}
