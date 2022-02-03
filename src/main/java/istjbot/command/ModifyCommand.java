package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

public class ModifyCommand extends Command {

    public ModifyCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length != 2) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(commandInfo[1]);
        } catch (NumberFormatException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }
        if (taskNumber < 1 || taskNumber > tasks.tasksSize()) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }

        switch (this.getCommandEnum()) {
        case MARK:
            tasks.markTask(taskNumber);
            storage.save(tasks);
            ui.showTaskMarked(tasks.taskString(taskNumber));
            break;

        case UNMARK:
            tasks.unmarkTask(taskNumber);
            storage.save(tasks);
            ui.showTaskUnmarked(tasks.taskString(taskNumber));
            break;

        case DELETE:
            String deletedTask = tasks.deletedTaskString(taskNumber);
            storage.save(tasks);
            ui.showTaskDeleted(tasks.tasksSize(), deletedTask);
            break;
        }
    }
}
