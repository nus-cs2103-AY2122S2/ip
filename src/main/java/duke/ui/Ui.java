package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

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

    public String getSetupMessage() {
        StringBuilder s = new StringBuilder();
        s.append("Hello! I'm\n");
        s.append(logo);
        s.append("\nWhat can I do for you? =)");
        return s.toString();
    }

    public String getByeMessage() {
        return "Bye t_t";
    }

    public String getFileErrorMessage() {
        return "Error loading/parsing file ?.? Creating empty list!";
    }

    public String getIoErrorMessage() {
        return "I/O error x.x";
    }

    public String getAddTaskMessage(Task latestTask, int size) {
        StringBuilder s = new StringBuilder();
        s.append("added o.O:\n  ");
        s.append(latestTask.toString());
        s.append("\nNow there are " + size + " tasks on the list x)");
        return s.toString();
    }

    public String readLine() {
        String command = sc.nextLine();
        return command;
    }

    public String printItems(ArrayList<Task> list) {
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