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
            int pos = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(pos).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("todo")) {
            String[] ls = input.split(" ", 2);
            if (ls.length <= 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String des = ls[1];
            return new AddToDoCommand(new Todo(des)).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("deadline")) {
            try {
                String des = input.split(" /", 2)[0].split(" ", 2)[1];
                String date = input.split("/", 2)[1].split(" ", 2)[1];
                return new AddDeadlineCommand(new Deadline(des, date)).execute(tasks, storage);
            } catch (DateTimeParseException e) {
                throw new DukeException("\t " + "The date format should be YYYY-MM-DD");
            }
        } else if (input.split(" ")[0].equals("event")) {
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            return new AddEventCommand(new Event(des, date)).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("delete")) {
            int pos = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(pos).execute(tasks, storage);
        } else if (input.split(" ")[0].equals("find")) {
            String keyword = input.split(" ", 2)[1];
            return new FindCommand(keyword).execute(tasks, storage);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
