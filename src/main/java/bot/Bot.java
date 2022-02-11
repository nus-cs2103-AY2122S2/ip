package bot;

import commands.Command;

import parser.Parser;

import tasklist.TaskList;

import ui.Ui;

public class Bot {
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    public Bot(Parser parser, Ui ui, TaskList taskList) {
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                final String query = ui.readCommand();
                final Command command = this.parser.parse(query);
                command.execute(this.ui, this.taskList);
                isExit = command.isExit();
            } catch (Exception ex) {
                ui.showError(ex.getMessage());
            }
        }
    }
}
