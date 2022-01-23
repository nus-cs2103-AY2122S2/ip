package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;
import saitama.exceptions.SaitamaException;

abstract public class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws SaitamaException;
    abstract public boolean isExit();
}