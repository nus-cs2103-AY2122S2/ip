import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    AddCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length == 1) {
            throw new BotException("As an IstjBot, I cannot find any description for your task.");
        }
        
        String description = "";
        int modifier = -1;
        boolean modifierFound = false;
        String modifierMessage = "";

        for (int i = 1; i < commandInfo.length; i++) {
            if (this.getCommandEnum() == CommandEnum.DEADLINE && commandInfo[i].equals("/by")) {
                modifier = i;
                modifierFound = true;
                break;
            } else if (this.getCommandEnum() == CommandEnum.EVENT && commandInfo[i].equals("/at")) {
                modifier = i;
                modifierFound = true;
                break;
            }
            if (i == 1) {
                description += commandInfo[i];
            } else {
                description += " " + commandInfo[i];
            }
        }

        // Error handle for modifier and description
        if (description.equals("")) {
            throw new BotException("As an IstjBot, I cannot add a task with no description.");
        } else if (!modifierFound && (this.getCommandEnum() == CommandEnum.DEADLINE
                || this.getCommandEnum() == CommandEnum.EVENT)) {
            throw new BotException("As an IstjBot, I cannot add a special task with no timing attached.");
        }

        if (this.getCommandEnum() == CommandEnum.DEADLINE || this.getCommandEnum() == CommandEnum.EVENT) {
            for (int i = modifier + 1; i < commandInfo.length; i++) {
                modifierMessage += i == commandInfo.length - 1 ? commandInfo[i] : commandInfo[i] + " ";
            }
        }

        // Error handle for modifier message
        if (modifierMessage.equals("") && (this.getCommandEnum() == CommandEnum.DEADLINE
                || this.getCommandEnum() == CommandEnum.EVENT)) {
            throw new BotException("As an IstjBot, I cannot add a special task with no timing attached.");
        }

        // TaskList
        try {
            tasks.addTask(this.getCommandEnum(), description, modifierMessage);
        } catch (DateTimeParseException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper date you entered.");
        }

        // Storage
        storage.save(tasks);

        // Ui
        ui.showTaskAdded(tasks.tasksSize(), tasks.TaskString(tasks.tasksSize()));
    }
}
