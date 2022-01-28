package main.java.ari.command;

import java.time.LocalDate;

import main.java.ari.tasks.DeadlineTask;

/**
 * Adds a DeadlineTask to TaskList
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String taskDesc, String timeDate, LocalDate date) {
        this.task = new DeadlineTask(taskDesc, timeDate, date);
    }
}
