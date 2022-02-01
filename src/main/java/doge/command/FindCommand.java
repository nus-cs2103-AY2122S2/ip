package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;

import java.util.Locale;

public class FindCommand extends Command {
    String message;

    public FindCommand(String s) {
        super(s);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.details.isEmpty()) {
            throw new DogeException("How am I supposed to find something if you don't give me the details?");
        }

        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        int numbering = 1;

        for (int i = 0; i < tasks.size(); i++) {
            String curr = tasks.getTask(i).getDescription();
            if (curr.contains(this.details)) {
                output.append("\n").append(numbering).append(") ➜ ").append(tasks.getTask(i).toString());
                numbering++;
            }
        }

        this.message = output.toString();
    }

    @Override
    public String toString() {
        return this.message;
    }
}
