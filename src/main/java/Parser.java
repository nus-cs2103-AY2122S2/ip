public class Parser {

    public Parser() {
    }

    public static String formatTaskToStore(Task task) {
        String prefix;
        String data;
        if (task instanceof Deadline) {
            prefix = TaskConstant.PREFIX_DEADLINE;
            data = prefix + Constant.VERTICAL_BAR_WITH_SPACE + task.getStatusIcon() + Constant.VERTICAL_BAR_WITH_SPACE +
                    task.getDescription() + Constant.VERTICAL_BAR_WITH_SPACE + ((Deadline) task).getBy() +
                    Constant.LINE_SEPARATOR;
        } else if (task instanceof Event) {
            prefix = TaskConstant.PREFIX_EVENT;
            data = prefix + Constant.VERTICAL_BAR_WITH_SPACE + task.getStatusIcon() + Constant.VERTICAL_BAR_WITH_SPACE +
                    task.getDescription() + Constant.VERTICAL_BAR_WITH_SPACE + ((Event) task).getAt() +
                    Constant.LINE_SEPARATOR;
        } else {
            prefix = TaskConstant.PREFIX_TODO;
            data = prefix + Constant.VERTICAL_BAR_WITH_SPACE + task.getStatusIcon() + Constant.VERTICAL_BAR_WITH_SPACE +
                    task.getDescription() + Constant.LINE_SEPARATOR;
        }
        return data;
    }

    public static Task retrieveTaskFromStoredData(String data) {
        String[] tokens = data.split(Constant.VERTICAL_BAR_REGEX);
        String prefix = tokens[0];
        boolean isDone = tokens[1].equals(TaskConstant.STATUS_ICON_DONE);
        String description = tokens[2];

        Task task;

        if (prefix.equals(TaskConstant.PREFIX_DEADLINE)) {
            String by = tokens[3];
            task =  new Deadline(description, by);
        } else if (prefix.equals(TaskConstant.PREFIX_EVENT)) {
            String at = tokens[3];
            task = new Event(description, at);
        } else {
            task = new Todo(description);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;

    }

    public static String[] parseUserInput(String userInput) throws EmptyTaskException {
        final String[] args = userInput.strip().split(" ", 2);
        String command = args[0];
        boolean isCommandAdd = command.equals(Constant.COMMAND_TODO) || command.equals(Constant.COMMAND_DEADLINE) ||
                command.equals(Constant.COMMAND_EVENT);

        if (args.length == 2) {
            return args;
        } else if (isCommandAdd) {
            throw new EmptyTaskException();
        } else {
            return new String[] {args[0], ""};
        }
    }
}

