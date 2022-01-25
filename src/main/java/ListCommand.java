public class ListCommand extends Command {
    ListCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length > 1) {
            throw new BotException("As an IstjBot, I cannot understand more than list.");
        }

        String list = tasks.tasksToString();

        ui.showTasks(list);
    }
}