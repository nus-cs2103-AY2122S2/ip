package chatcat.commands;

import java.util.ArrayList;

import chatcat.util.UI;
import chatcat.tasks.Task;

public class Commands {
    UI ui = new UI();

    public void outputList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            ui.printOutPutWithSpace("empty list!");
        } else {
            ui.printOutPutWithoutSpace("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.printOutPutWithoutSpace((i + 1) + ". " + tasks.get(i));
            }
        }
        ui.printOutPutWithoutSpace("");
    }

    public void outputByeMessage() {
        ui.printOutPutWithSpace("Bye. Hope to see you again soon!");
    }

    public void outputMarkMessage(Task task) {
        ui.printOutPutWithSpace("Nice! I've marked this task as done:\n"
                + task);
    }

    public void outputUnmarkMessage(Task task) {
        ui.printOutPutWithSpace("OK, I've marked this task as not done yet:\n"
                + task);
    }

    public void outputTaskMessage(Task task, int size) {
        ui.printOutPutWithSpace("Got it. I've added this task:\n"
                + task);
        ui.printOutPutWithSpace("Now you have "
                + size + " tasks in the list.");
    }

    public void outputDeleteMessage(Task task, int size) {
        ui.printOutPutWithSpace("Noted. I've removed this task:\n"
                + task);
        ui.printOutPutWithSpace("Now you have " + size
                + " tasks in the list.");
    }

    public void outputFilterMessage(ArrayList<Task> tasks) {
        ui.printOutPutWithoutSpace("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.printOutPutWithoutSpace((i + 1) + ". " + tasks.get(i));
        }
        ui.printOutPutWithoutSpace("");
    }
}
