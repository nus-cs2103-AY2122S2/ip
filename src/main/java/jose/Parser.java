package jose;

public class Parser {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else {
            String[] task = input.split(" ");
            String command = task[0];
            if (command.equals("mark")) {
                return Command.MARK;
            } else if (command.equals("unmark")) {
                return Command.UNMARK;
            } else if (command.equals("delete")) {
                return Command.DELETE;
            } else {
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (task.length > 1) {
                        if (command.equals("todo")) {
                            return Command.TODO;
                        } else if (command.equals("deadline")) {
                            return Command.DEADLINE;
                        } else {
                            return Command.EVENT;
                        }
                    } else {
                        throw new DukeException(command + " requires additional info");
                    }
                } else {
                    throw new DukeException("Nani?! No comprende por favor");
                }
            }
        }
    }

    public int getIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}
