package main.duke.commands;

import main.duke.DukeException;
import main.duke.TaskList;
import main.duke.Ui;
import main.duke.enums.CommandType;

public abstract class Command {
    protected CommandType commandType;

    protected static boolean isExit = false;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public static boolean getIsExit() { return Command.isExit; }

    public static void exitDuke() { Command.isExit = true; }

    public CommandType getCommandType() { return this.commandType; }

    public abstract void runCommand(Ui ui, TaskList taskList) throws DukeException;
}
