
public class Parser {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Parser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    public static Command parse(String input) {

        try {
            String[] inputWords = input.split("\\s");
            Action action = Action.valueOf(inputWords[0].toUpperCase());    //action is first word of the input
            switch (action) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case TODO:
                    return new AddCommand(Action.TODO, input);
                case DEADLINE:
                    return new AddCommand(Action.DEADLINE, input);
                case EVENT:
                    return new AddCommand(Action.EVENT, input);
                case MARK:
                    return new MarkCommand(inputWords);
                case UNMARK:
                    return new UnmarkCommand(inputWords);
                case DELETE:
                    return new DeleteCommand(inputWords);
                default:
                    return new InvalidCommand();
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }
    }

//    public String parseTodo(String input) {
//
//        try {
//            String[] todoArr = input.split("\\s", 2);
//            if (todoArr.length <= 1) {
//                throw new InvalidArgumentException(Messages.UNKNOWN_TODO);
//            }
//            tasks.add(new Todo(todoArr[1].trim()));
//            tasks.printTaskAdded();
//        } catch (InvalidArgumentException e) {
//            ui.showError(e.getMessage());
//        }
//    }
}
