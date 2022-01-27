public class ByeCommand implements Command {
    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE = "Bye. See you. <O_O>";

    public ByeCommand(String input) throws UltoiException {
        if (! input.equals(COMMAND_BYE)) {
            throw UltoiException.commandMismatchException();
        }
    }

    @Override
    public void execute(UltoiUi ui, TaskList tasks, Storage storage) {
        ui.showMsg(this.MESSAGE);
        return;
    }
}
