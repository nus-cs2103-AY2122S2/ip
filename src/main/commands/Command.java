package main.commands;

import main.DukeException;
import main.TaskList;
import main.Ui;
import main.enums.CommandType;

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
