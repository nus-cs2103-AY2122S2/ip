package com.duke;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMsg("\t Here are the tasks in your list:");
        for (int i=0; i<tasks.getCount(); i++) {
            ui.showMsg("\t " + (i+1) + "." + tasks.get(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
