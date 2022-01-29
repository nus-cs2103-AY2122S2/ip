package paggro.command;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;
import paggro.exception.PaggroException;

public abstract class Command {
    String parameters;

    public Command() {
    }

    public Command(String parameters) {
        this.parameters = parameters;
    }

    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    public abstract void execute(Lister lister, Ui ui, Storage storage) throws PaggroException;
}
