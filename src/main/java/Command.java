public abstract class Command {
    private final String message;

    public Command(String message) {
        this.message = message;
    }

    abstract public void execute(UltoiUI ui);
}
