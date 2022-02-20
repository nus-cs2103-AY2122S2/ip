package siri;

import java.util.ArrayList;

public class FindCommand extends Command{
    protected String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            String taskDescription = task.description;
            boolean val = taskDescription.contains(keyWord);

            if (val) {
                foundTasks.add(task);
            }
        }

        ui.showFind(foundTasks);
    }

}
