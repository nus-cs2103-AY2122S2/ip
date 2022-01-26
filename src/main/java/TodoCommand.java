public class TodoCommand extends AddCommand {

    public static final CommandAction COMMAND_ACTION = CommandAction.TODO;

    TodoCommand(Todo todo) {
        super(todo);
    }
}
