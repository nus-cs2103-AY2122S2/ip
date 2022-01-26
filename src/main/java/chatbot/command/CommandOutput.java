package chatbot.command;

public class CommandOutput {
    public final String output;
    public final boolean isListEdited;

    public CommandOutput(String output, boolean isListEdited) {
        this.output = output;
        this.isListEdited = isListEdited;
    }
}