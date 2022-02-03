package duke.commands;

public class IncorrectCommand extends Command {
    
    private String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

}
