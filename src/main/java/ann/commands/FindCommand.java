package ann.commands;

public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    public static final String FORMAT = KEYWORD + " [keyword(s)]";
    private String findKeyWords;

    public FindCommand(String kw) {
        findKeyWords = kw;
    }

    @Override
    public void executeCommand() {
        super.setMessage(super.taskList.findTask(this.findKeyWords));
    }
}
