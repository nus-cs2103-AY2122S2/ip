package duke.commands;

/**
 * Command for any unknown commands.
 */
public class InvalidCommand extends Command{

    /**
     * returns String to tell user command is unknown.
     *
     * @return Message for completing the command which is displayed to user.
     */
    @Override
    public String execute(){
        return "I am unable to comprehend your request. Please try again";
    }
}
