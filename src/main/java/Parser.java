public class Parser {
    public static Command parseCommand(String command) throws CommandNotFoundException {
        command = command.toLowerCase();
        if (command.equals("list")) {
            return Command.LIST;
        } else if(command.equals("mark")) {
            return Command.MARK;
        } else if(command.equals("unmark")) {
            return Command.UNMARK;
        } else if (command.equals("event")){
            return Command.EVENT;
        } else if (command.equals("deadline")){
            return Command.DEADLINE;
        } else if (command.equals("todo")) {
            return Command.TODO;
        } else if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("delete")) {
            return Command.DELETE;
        } else {
            throw new CommandNotFoundException("Unrecognised Command");
        }
    }

    public static String[] parseInput(String input) {
        String[] inputs = new String[2];
        if (input.indexOf("/by") != -1) {
            int indexOfTime = input.indexOf("/by");
            inputs[0] = input.substring(0,indexOfTime);
            inputs[1] = input.substring(indexOfTime+4);
        } else if (input.indexOf("/at") != -1) {
            int indexOfTime = input.indexOf("/at");
            inputs[0] = input.substring(0,indexOfTime);
            inputs[1] = input.substring(indexOfTime+4);
        } else {
            inputs[0] = input;
            inputs[1] = "";
        }
        return inputs;
    }
}
