public class Input {
    enum States {
        ECHO,
        ADD,
        LIST,
        BYE
    }

    private States states;
    private String args;
    private String input;

    public Input(String input) {
        if (input.equalsIgnoreCase("bye")) {
            this.states = States.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            this.states = States.LIST;
        } else {
            this.states = States.ADD;
        }

        this.args = this.states == States.ADD ? input : "";
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
