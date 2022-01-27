package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.Storage;
import com.duke.util.Ui;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i=0; i<tasks.getCount(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                System.out.println("\t " + tasks.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
