package seedu.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public abstract class Command {
    public static String run(String inputCommand, TaskList taskList) throws DukeException, IOException {
        return inputCommand;
    }
}