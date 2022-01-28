package main.java.ari.command;

import java.time.LocalDate;

import main.java.ari.tasks.EventTask;

/**
 * Adds an EventCommand to TaskList
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    public EventCommand(String taskDesc, String timeDate, LocalDate date) {
        this.task = new EventTask(taskDesc, timeDate, date);
    }

}
