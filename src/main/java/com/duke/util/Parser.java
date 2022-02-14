package com.duke.util;

import com.duke.command.*;
import com.duke.exception.DukeException;
import com.duke.task.Deadline;
import com.duke.task.Event;
import com.duke.task.TaskList;
import com.duke.task.Todo;

import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parse the input by the user.
     * @param input message input by the user.
     * @return Command that can execute according to the user's input.
     * @throws DukeException if there is invalid argument.
     */
    public static String parseInput(String input, TaskList tasks, Storage storage) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand().execute(tasks, storage);
        } else if (input.equals("list")) {
            return new ListCommand().execute(tasks, storage);
        } else if (input.split(" ")[0].equals("mark")) {
            return new MarkCommand(input).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("todo")) {
            return new AddToDoCommand(input).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("deadline")) {
            return new AddDeadlineCommand(input).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("event")) {
            return new AddEventCommand(input).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("delete")) {
            return new DeleteCommand(input).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("find")) {
            return new FindCommand(input).execute(tasks, storage);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
