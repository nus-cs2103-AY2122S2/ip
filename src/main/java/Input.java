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
        this.states = input.equalsIgnoreCase("bye") ? States.BYE : States.ECHO;
        this.args = "";
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
