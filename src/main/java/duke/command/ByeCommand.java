package duke.command;

public class ByeCommand extends Command<String>{

    public ByeCommand() {
        runCommand();
    }


    public void runCommand() {
        super.changeRunning(false);
    }
}
