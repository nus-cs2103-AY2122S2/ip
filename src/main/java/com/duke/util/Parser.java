package com.duke.util;

import com.duke.command.*;
import com.duke.exception.DukeException;
import com.duke.exception.ToDoException;
import com.duke.task.Deadline;
import com.duke.task.Event;
import com.duke.task.Todo;

public class Parser {

    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.split(" ")[0].equals("mark")) {
            int pos = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(pos);
        } else if (input.split(" ")[0].equals("todo")) {
            String[] ls = input.split(" ");
            if (ls.length <= 1) {
                throw new ToDoException();
            }
            String des = ls[1];
            return new AddToDoCommand(new Todo(des));
        } else if (input.split(" ")[0].equals("deadline")) {
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            return new AddDeadlineCommand(new Deadline(des, date));
        } else if (input.split(" ")[0].equals("event")) {
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            return new AddEventCommand(new Event(des, date));
        } else if (input.split(" ")[0].equals("delete")) {
            int pos = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(pos);
        } else {
            throw new DukeException();
        }
    }
}
