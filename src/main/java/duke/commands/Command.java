package duke.commands;

import duke.main.DukeException;
import duke.main.TaskList;

public abstract class Command<E> {
    public abstract void runCommand(TaskList todoList, E cmd) throws DukeException;
}
