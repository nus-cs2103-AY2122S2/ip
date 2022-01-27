package ann.commands;

import ann.data.tasks.TaskType;
import ann.data.tasks.Task;
import ann.data.tasks.Event;
import ann.data.tasks.Deadline;
import ann.data.TaskList;


public class AddCommand extends Command{
    private TaskType taskType;
    private Task task;

    public AddCommand(TaskType tt, String[] taskComponents) {
        taskType = tt;
        setTask(taskComponents);
    }

    private void setTask(String[] taskComponents) {
        switch (taskType) {
            case TODO:
                task = new Task(taskComponents[0]);
                break;
            case DEADLINE:
                task = new Deadline(taskComponents[0], taskComponents[1]);
                break;
            case EVENT:
                task = new Event(taskComponents[0], taskComponents[1]);
        }
    }

    @Override
    public void executeCommand() {
        super.taskList.addTask(task);
        super.setMessage("Alright! I've added this task:\n" + task.toString()
                + "\n" + taskList.getNumTasksString());
    }
}