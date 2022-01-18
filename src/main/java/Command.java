public class Command {
    private static final String[] commands = {
            "mark", "unmark"
    };

    /**
     * Checks whether a command is valid
     * @param cmd Command to check validity
     * @return Validity of the command
     */
    public static Boolean isValidCommand(String cmd) {
        for (String command : Command.commands) {
            if (command.equals(cmd)) return true;
        }

        return false;
    }

    private String name;
    private String[] arguments;

    public Command(String name) {
        this(name, new String[0]);
    }

    public Command(String name, String ...args) {
        this.name = name;
        this.arguments = args;
    }

    public String getName() {
        return this.name;
    }

    public String[] getArgs() {
        return this.arguments;
    }
}
