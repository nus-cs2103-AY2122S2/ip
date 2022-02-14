package com.duke.command;

import com.duke.exception.DukeException;
import com.duke.task.Deadline;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.Storage;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the command to add deadline task into the list.
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            tasks.add(new Deadline(des, date));
            return "Got itm I've added this tasks:\n " + tasks.get(tasks.getCount()-1)
                    + "\n" + "Now you have " + tasks.getCount() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new DukeException("\t " + "The date format should be YYYY-MM-DD");
        }

    }
}
