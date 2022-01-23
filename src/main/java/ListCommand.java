public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_FORMAT = COMMAND_WORD;

    @Override
    public CommandFeedback execute(TaskList taskList) throws InvalidArgumentException {
        return new CommandFeedback(CommandType.LIST, taskList);
    }
}
