package duke.commands;

/**
 * Command for any unknown commands.
 */
public class InvalidCommand extends Command{

    final private String MESSAGE = "I am unable to comprehend your request. Please try again";

    /**
     * returns String to tell user command is unknown.
     *
     * @return Message for completing the command which is displayed to user.
     */
    @Override
    public String execute(){
        return MESSAGE;
    }
}
