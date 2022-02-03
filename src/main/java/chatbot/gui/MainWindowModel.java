package chatbot.gui;

import chatbot.command.CommandList;
import chatbot.command.DeadlineCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.HelpCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.ResetCommand;
import chatbot.command.TerminateCommand;
import chatbot.command.ToDoCommand;
import chatbot.command.UnmarkCommand;
import chatbot.task.TaskList;

public class MainWindowModel {
    public static final String SAVE_FILE = "./data/save_file";

    private final CommandList commandList;
    private final TaskList taskList;

    public MainWindowModel() {
        this.taskList = TaskList.create(SAVE_FILE);
        this.commandList = new CommandList();
        this.commandList.addCommand(new TerminateCommand());
        this.commandList.addCommand(new DeadlineCommand());
        this.commandList.addCommand(new DeleteCommand());
        this.commandList.addCommand(new EventCommand());
        this.commandList.addCommand(new ListCommand());
        this.commandList.addCommand(new MarkCommand());
        this.commandList.addCommand(new ToDoCommand());
        this.commandList.addCommand(new UnmarkCommand());
        this.commandList.addCommand(new ResetCommand());
        this.commandList.addCommand(new FindCommand());
        this.commandList.addCommand(new HelpCommand());
    }

    public CommandList getCommandList() {
        return commandList;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
