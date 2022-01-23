public class Input {
    enum States {
        ECHO,
        ADD,
        LIST,
        TOGGLE,
        DELETE,
        SEARCH,
        BYE
    }

    private States states;
    private String args;
    private String input;

    public Input(String input) {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        String args = inputArr.length > 1 ? inputArr[1] : "";

        if (command.equalsIgnoreCase("bye")) {
            this.states = States.BYE;
        } else if (command.equalsIgnoreCase("list")) {
            this.states = States.LIST;
        } else if (command.equalsIgnoreCase("mark") ||
                command.equalsIgnoreCase("unmark")) {
            this.states = States.TOGGLE;
        } else if (command.equalsIgnoreCase("delete")) {
            this.states = States.DELETE;
        } else if (command.equalsIgnoreCase("search")) {
            this.states = States.SEARCH;
        } else {
            this.states = States.ADD;
        }

        this.args = args;
        this.input = input;
    }

    public States getStates() {
        return this.states;
    }

    public String getArgs() {
        return this.args;
    }

    public String getInput() {
        return this.input;
    }
}
