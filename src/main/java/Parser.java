class Parser {
    public static Command parse(String str) {
        String[] cmd = str.split("\\s", 2);
        String[] fields;

        switch (cmd[0]) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(Integer.parseInt(cmd[1]));

        case "unmark":
            return new UnmarkCommand(Integer.parseInt(cmd[1]));

        case "delete":
            return new DeleteCommand(Integer.parseInt(cmd[1]));

        case "todo":
            return new AddCommand(cmd[1]);

        case "deadline":
            fields = cmd[1].split(" /by ");
            return new AddCommand(Task.Type.DEADLINE, fields[0], fields[1]);

        case "event":
            fields = cmd[1].split(" /at ");
            return new AddCommand(Task.Type.EVENT, fields[0], fields[1]);

        default:
            return new InvalidCommand();
        }
    }
}
