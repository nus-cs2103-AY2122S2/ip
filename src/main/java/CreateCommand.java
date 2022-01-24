import java.time.LocalDateTime;

public class CreateCommand extends Command {
    private final TaskType taskType;
    public CreateCommand(String args, TaskType taskType) {
        super(args);
        this.taskType = taskType;
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        if (this.taskType == TaskType.TODO) {
            this.parseCreateTodo(linePrinter, this.args, taskList);
        } else if (this.taskType == TaskType.DEADLINE) {
            this.parseCreateDeadline(linePrinter, this.args, taskList);
        } else if (this.taskType == TaskType.EVENT) {
            this.parseCreateEvent(linePrinter, this.args, taskList);
        }

        return true;
    }

    private void parseCreateTodo(IPrintable linePrinter, String args, TaskList taskList)
            throws DukeIllegalArgumentException {
        // Syntax Check
        if (args.equals("")) {
            throw new DukeIllegalArgumentException("Task name cannot be empty");
        }

        final Task task = taskList.addTask(new Todo(args));
        this.printResponse(linePrinter, task, taskList, "Todo");
    }

    private void parseCreateDeadline(IPrintable linePrinter, String args, TaskList taskList)
            throws DukeIllegalArgumentException {
        // Syntax Check
        final String[] argParts = args.split(" /by ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <Task name> /by <Date>");
        }

        final String taskDescription = argParts[0];
        final LocalDateTime taskBy = parseDateTime(argParts[1]);
        final Task task = taskList.addTask(new Deadline(taskDescription, taskBy));
        this.printResponse(linePrinter, task, taskList, "Deadline");
    }

    private void parseCreateEvent(IPrintable linePrinter, String args, TaskList taskList)
            throws DukeIllegalArgumentException {
        // Syntax Check
        final String[] argParts = args.split(" /at ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <Task name> /at <Date>");
        }

        final String taskDescription = argParts[0];
        final LocalDateTime taskAt = parseDateTime(argParts[1]);
        final Task task = taskList.addTask(new Event(taskDescription, taskAt));
        this.printResponse(linePrinter, task, taskList, "Event");
    }

    private void printResponse(IPrintable linePrinter, Task task, TaskList taskList, String type) {
        linePrinter.print(String.format("Added the following %s Task:", type));
        linePrinter.print(String.format("\t%s", task.getReadableString()));
        linePrinter.print(String.format("Now you have %d task(s) in the list", taskList.getTaskCount()));
    }
}
