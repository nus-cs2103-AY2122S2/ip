package duke;

public class CommandHelp extends Command {

    public CommandHelp() {

    }

    @Override
    public String execute() {
        return "try the following commands:\n"
                + "   list (list out all tasks in your todo list)\n"
                + "   todo <task> (add a basic task to your todo list)\n"
                + "   deadline <task> /by <yyyy-mm-dd> (add a task with a deadline to your todo list)\n"
                + "   event <task> /at <yyyy-mm-dd> (add a new event with its corresponding date to your todo list)\n"
                + "   mark <task index> (mark that specific task as completed)\n"
                + "   unmark <task index> (remove mark from specific task)\n"
                + "   delete <task index> (remove task from todo list)\n"
                + "   bye (close application)\n";
    }
}
