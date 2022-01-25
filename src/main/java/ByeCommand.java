public class ByeCommand extends Command {

    ByeCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length > 1) {
            throw new BotException("As an IstjBot, I cannot understand more than bye.");
        }

        ui.showBye();
    }
}