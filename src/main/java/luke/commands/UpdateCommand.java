package luke.commands;

public abstract class UpdateCommand extends Command {

    private final int index;

    UpdateCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
