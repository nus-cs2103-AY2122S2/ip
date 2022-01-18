public class Command {
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
