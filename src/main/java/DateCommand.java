import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    DateCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length != 2) {
            throw new BotException("As an IstjBot, I don't think that is a proper date you entered.");
        }

        try {
            String searchList = tasks.searchByDateString(commandInfo[1]);
            ui.showTasksByDate(searchList);
        } catch (DateTimeParseException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper date you entered.");
        }
    }
}