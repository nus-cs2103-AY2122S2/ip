package duke.commands;

public class InvalidCommand extends Command{

    final private String MESSAGE = "I am unable to comprehend your request. Please try again";

    @Override
    public String execute(){
        return MESSAGE;
    }
}
