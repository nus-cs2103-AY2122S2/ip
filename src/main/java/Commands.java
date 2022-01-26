import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Commands {

    private final TaskHistory taskHistory = new TaskHistory();

    public Commands() { // Empty Constructor
    }

    void bye() { // Get DukeLCH to Exit
        String bye = "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }

    void list() { // Get DukeLCH to List cmdHistory
        String border = "_______________________________________________________\n";
        System.out.println(border
                + "These are your tasks that we have in our records:\n"
                + taskHistory.printAll() + border);
    }

    void mark(int index) {
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

    void unmark(int index) {
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

    void todo(String[] tokens) {
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            description = description.concat(tokens[i]);
            if (i != (tokens.length - 1)) {
                description = description.concat(" ");
            }
        }
        taskHistory.addToDo(description);
    }

    void deadline(String[] tokens) throws DukeException {
        String description = "";
        String timeFrame = "";
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

        // Check for timeFrame
        if (timeStart == -1) {
            throw new DukeException("'/by' not detected");
        }

        for (int j = timeStart + 1; j < tokens.length; j++) {
            timeFrame = timeFrame.concat(tokens[j]);
            if (j != (tokens.length - 1)) {
                timeFrame = timeFrame.concat(" ");
            }
        }
        taskHistory.addDeadline(description, timeFrame);
    }

    void event(String[] tokens) throws DukeException {
        String description = "";
        String timeFrame = "";
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

        for (int j = timeStart + 1; j < tokens.length; j++) {
            timeFrame = timeFrame.concat(tokens[j]);
            if (j != (tokens.length - 1)) {
                timeFrame = timeFrame.concat(" ");
            }
        }
        taskHistory.addEvent(description, timeFrame);
    }

    void delete(int index) {
        taskHistory.deleteTask(index);
    }

    void startup() throws IOException {
        String home = System.getProperty("user.home");

        Path path = Paths.get(home,"Duke", "data");
        boolean directoryExists = Files.exists(path);
        System.out.println(path);
        System.out.println(directoryExists);

        String filePath = String.valueOf(path);

        File f1 = new File(filePath);
        if (f1.mkdirs()) {
            System.out.println("Successfully created data folder");
        } else {
            System.out.println("Creation of data folder was unsuccessful");
        }
        System.out.println(filePath);

        File f2 = new File (filePath + "\\duke.txt");

        if (f2.createNewFile()) {
            System.out.println("Successfully created duke.txt");
        } else {
            System.out.println("Creation of duke.txt was unsuccessful");
        }
    }
}
