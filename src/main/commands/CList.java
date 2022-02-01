package main.commands;

import main.enums.CommandType;
import main.tasks.Task;

public class CList extends Command{
    public CList() {
        super(CommandType.LIST);
    }

    @Override
    public void runCommand() {
        int n = Task.getTasksCount();
        if (n == 0) {
            System.out.println("The list is currently empty.");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.printf("%d.%s%n", i + 1, Task.getTask(i));
            }
        }
    }
}
