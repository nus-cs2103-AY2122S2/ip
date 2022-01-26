public class ListCommand extends Command {

    public static final CommandAction COMMAND_ACTION = CommandAction.LIST;

    @Override
    public CommandResult execute(TaskList taskList) {
        String msg = "Yay! You have no task to do!";
        if (!taskList.isEmpty()) {
            msg = "Currently, you have the following tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                msg += String.format("\t%d. %s\n", i + 1, taskList.get(i));
            }
            msg = msg.stripTrailing();
        }
        return new CommandResult(msg);
    }
}
