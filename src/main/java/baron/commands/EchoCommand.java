package baron.commands;

public class EchoCommand extends Command {
    private String echoString = "";

    public EchoCommand(String echoString) {
        this.echoString = echoString;
    }

    @Override
    public String execute() {
        return this.echoString;
    }
}
