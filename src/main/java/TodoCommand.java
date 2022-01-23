public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task name>";

    public TodoCommand(Todo newTodo) {
        super(newTodo);
    }
}

