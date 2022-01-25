import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Ui {
    private Scanner sc;
    String logo;

    public Ui() {
        sc = new Scanner(System.in);
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    protected String getSetupMessage() {
        StringBuilder s = new StringBuilder();
        s.append("Hello! I'm\n");
        s.append(logo);
        s.append("\nWhat can I do for you? =)");
        return s.toString();
    }

    protected String getByeMessage() {
        return "Bye t_t";
    }

    protected String getFileErrorMessage() {
        return "Error loading/parsing file ?.? Creating empty list!";
    }

    protected String getIoErrorMessage() {
        return "I/O error x.x";
    }

    protected String getAddTaskMessage(Task latestTask, int size) {
        StringBuilder s = new StringBuilder();
        s.append("added o.O:\n  ");
        s.append(latestTask.toString());
        s.append("\nNow there are " + size + " tasks on the list x)");
        return s.toString();
    }

    protected String readLine() {
        String command = sc.nextLine();
        return command;
    }

    protected String printItems(ArrayList<Task> list) {
        if (list.size() == 0) {
            return("There are no tasks on your list :O");
        }
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks on your list :O\n");
        for (int i = 1; i <= list.size(); i++) {
            s.append(i);
            s.append(". ");
            s.append(list.get(i-1));
            if (i < list.size()) {
                s.append("\n");
            }
        }
        return s.toString();
    }
}