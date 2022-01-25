package commands;

import main.java.UI;

public abstract class Command {
    public Command() {

    }

    public Command of(String cmdString) {

    }

    public abstract void execute(UI ui);
}
