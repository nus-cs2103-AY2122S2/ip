package chatcat.commands;

import java.util.ArrayList;

import chatcat.util.Ui;
import chatcat.tasks.Task;

public class Commands {
    Ui ui = new Ui();

    public String outputList(ArrayList<Task> tasks) {
        StringBuffer str = new StringBuffer();

        if (tasks.size() == 0) {
            str.append(ui.printOutPutWithSpace("empty list!"));
        } else {
            str.append(ui.printOutPutWithoutSpace("Here are the tasks in your list:") + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                str.append(ui.printOutPutWithoutSpace((i + 1) + ". " + tasks.get(i)) + "\n");
            }
        }
        str.append(ui.printOutPutWithoutSpace(""));

        return str.toString();
    }

    public String outputByeMessage() {
        return (ui.printOutPutWithSpace("Bye. Hope to see you again soon!"));
    }

    public String outputMarkMessage(Task task) {
        return (ui.printOutPutWithSpace("Nice! I've marked this task as done:\n"
                + task));
    }

    public String outputUnmarkMessage(Task task) {
        return (ui.printOutPutWithSpace("OK, I've marked this task as not done yet:\n"
                + task));
    }

    public String outputTaskMessage(Task task, int size) {
        return ui.printOutPutWithSpace("Got it. I've added this task:\n" + task) + "\n" +
                ui.printOutPutWithSpace("Now you have " +
                size + " tasks in the list.");
    }

    public String outputDeleteMessage(Task task, int size) {
        return ui.printOutPutWithSpace("Noted. I've removed this task:\n" + task) + "\n" +
                ui.printOutPutWithSpace("Now you have " + size +
                " tasks in the list.");
    }

    public String outputFilterMessage(ArrayList<Task> tasks) {
        StringBuffer str = new StringBuffer();

        str.append(ui.printOutPutWithoutSpace("Here are the matching tasks in your list:" + "\n"));
        for (int i = 0; i < tasks.size(); i++) {
            str.append(ui.printOutPutWithoutSpace((i + 1) + ". " + tasks.get(i)) + "\n");
        }
        str.append(ui.printOutPutWithoutSpace(""));

        return str.toString();
    }
}
