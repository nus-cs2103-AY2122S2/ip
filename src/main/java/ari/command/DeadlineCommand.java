package ari.command;

import java.time.LocalDate;

import ari.tasks.DeadlineTask;

/**
 * Adds a DeadlineTask to TaskList
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String taskDesc, String timeDate, LocalDate date) {
        this.task = new DeadlineTask(taskDesc, timeDate, date);
    }
}
