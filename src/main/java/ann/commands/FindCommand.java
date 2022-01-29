package ann.commands;

public class FindCommand extends Command{
    private String findKeyWords;

    public FindCommand(String kw) {
        findKeyWords = kw;
    }

    @Override
    public void executeCommand() {
        super.setMessage(super.taskList.findTask(this.findKeyWords));
    }
}
