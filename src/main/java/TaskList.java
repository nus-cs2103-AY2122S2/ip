import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> itemList = new ArrayList<>(0);

    public TaskList() {}

    public void addTodo(String taskKey) {
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
            this.itemList.add(newTask);
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + this.itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
    }

    public void addEvent(String taskKey) {
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
            System.out.println(deadline);
            Task newTask = new EventTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            this.itemList.add(newTask);
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + this.itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
        } catch(DateTimeParseException err) {
            System.out.println(err.getMessage());
        }
    }

    public void addDeadline(String taskKey) {
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HHmm");
            Task newTask = new DeadlineTask(taskTitle, LocalDateTime.parse(deadline, formatter));
            this.itemList.add(newTask);
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + this.itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
        } catch(DateTimeParseException err) {
            System.out.println(err.getMessage());
        }
    }

    public void deleteTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        this.itemList.remove(index);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                        "Noted. I've removed this task:\n"
                        + "  " + targetTask + "\n"
                        + "Now you have " + this.itemList.size() + " tasks in the list."
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void markTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        Task newTask = targetTask.markTask();
        this.itemList.set(index, newTask);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                "Nice! I've marked this task as done:"
                        + "\n" + "  " + targetTask
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void unmarkTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        Task newTask = targetTask.unmarkTask();
        this.itemList.set(index, newTask);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                "OK, I've marked this task as not done yet:"
                        + "\n" + "  " + targetTask
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public String printList() {
        StringBuilder initList = new StringBuilder();

        for (int i = 1; i < itemList.size() + 1; i++) {
            initList.append(i + ".");
            initList.append(itemList.get(i - 1));
            initList.append("\n");
        }

        return initList.toString();
    }
}
