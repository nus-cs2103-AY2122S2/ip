package com.duke.command;

import com.duke.task.TaskList;
import com.duke.util.Storage;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = "Here are the matching tasks in your list:\n";
        for (int i=0; i<tasks.getCount(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                response = response + " " + tasks.get(i) + "\n";
            }
        }
        return response;
    }
}
