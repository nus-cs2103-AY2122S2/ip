package com.duke;

import com.duke.exception.DukeException;
import com.duke.task.TaskList;
import com.duke.util.Parser;
import com.duke.util.Storage;


/**
 * A chat robot which can save a todo list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String path) {
        storage = new Storage(path);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            return Parser.parseInput(input, tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
