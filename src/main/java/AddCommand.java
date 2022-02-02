import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command{
    private final String taskBody;
    private final String taskType;

    public AddCommand(String taskBody, String taskType) {
        this.taskBody = taskBody;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        switch (this.taskType) {
        case "todo":
            addTodo(taskBody, userInt, storage, tasks);
        case "deadline":
            addDeadline(taskBody, userInt, storage, tasks);
        case "event":
            addEvent(taskBody, userInt, storage, tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void addTodo(String taskKey, Ui userInt, Storage storage, TaskList itemList) {
        storage.writeToFile(taskKey, "T", false);
        String[] tokens = taskKey.split("todo ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("todo"); //type
            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        Task newTask = new TodoTask(taskTitle);
        itemList.add(newTask);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                        "Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + "Now you have " + itemList.size() + " tasks in the list."
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void addEvent(String taskKey, Ui userInt, Storage storage, TaskList itemList) {
        String[] tokens = taskKey.split("event ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("event");

            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        String[] secondSplit = taskTitle.split(" /at ");
        taskTitle = secondSplit[0];
        String deadline = "";
        try {
            if (secondSplit.length < 2) {
                throw new BadDeadlineException("event");
            } else {
                deadline = secondSplit[1];
            }
        } catch (BadDeadlineException err) {
            System.out.println(err.getMessage());
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            Task newTask = new EventTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            storage.writeToFile(taskKey, "E", false);
            itemList.add(newTask);
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
        } catch(DateTimeParseException err) {
            System.out.println(err.getMessage());
        }
    }

    public void addDeadline(String taskKey, Ui userInt, Storage storage, TaskList itemList) {
        String[] tokens = taskKey.split("deadline ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("deadline");
            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        String[] secondSplit = taskTitle.split(" /by ");
        taskTitle = secondSplit[0];
        String deadline = "";
        try {
            if (secondSplit.length < 2) {
                throw new BadDeadlineException("deadline");
            } else {
                deadline = secondSplit[1];
            }
        } catch (BadDeadlineException err) {
            System.out.println(err.getMessage());
            return;
        }

        try {
            //Accept string
            //Parse string to datetime, use formatter here to change reading style
            //when printing, check date time and print format.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            Task newTask = new DeadlineTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            storage.writeToFile(taskKey, "D", false);
            itemList.add(newTask);
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
        } catch(DateTimeParseException err) {
            System.out.println(err.getMessage());
        }
    }
}
