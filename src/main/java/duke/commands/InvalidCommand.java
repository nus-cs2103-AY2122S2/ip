package duke.commands;

public class InvalidCommand extends Command{

    @Override
    public String execute(){
        return "I am unable to comprehend your request. Please try again";
    }
}
