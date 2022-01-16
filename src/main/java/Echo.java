import java.util.ArrayList;

/**
 * Echo chatbot.
 */
public class Echo {

    /** ArrayList of task */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public Echo() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Reads input string and determines action to perform.
     *
     * @param s Input string from user.
     */
    public void read(String s) throws EchoException {
        String[] split = s.split(" ");
        String command = split[0];
        switch (command) {
            case "list":
                if (tasks.size() == 0) {
                    System.out.println("        Task list is empty!");
                } else {
                    for (int i = 0; i < tasks.size(); i++)
                        System.out.printf("        %d. %s%n", i + 1, tasks.get(i));
                }
                System.out.println("");
                break;
            case "mark":
            case "unmark":
            case "delete":
                if (split.length == 1)
                    throw new EchoException("Please specify the task number. Eg. mark 1, unmark 1, delete 1");
                try {
                    int taskIndex = Integer.parseInt(split[1]) - 1;
                    String message = "";
                    if (command.equals("mark")) {
                        tasks.get(taskIndex).mark();
                        message = "        Nice! The task is marked as done: ";
                    } else if (command.equals("unmark")) {
                        tasks.get(taskIndex).unmark();
                        message = "        OK! The task is unmarked.";
                    } else {
                        message = String.format("        %d. %s", taskIndex + 1, tasks.get(taskIndex).toString());
                        tasks.remove(taskIndex);
                        System.out.println("        Noted. I've removed the task:");
                    }
                    System.out.println(message);
                    if (command.equals("delete"))
                        System.out.printf("        Now you have %d tasks in the list.%n", tasks.size());
                    else
                        taskStatus(taskIndex);
                    System.out.println("");
                } catch (NumberFormatException nfe) {
                    System.out.println("        Second input must be an integer. Eg. mark 1, unmark 1, delete 1");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("        Task does not exist!");
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                String[] desc;
                if (split.length == 1)
                    throw new EchoException(String.format("The description of a %s cannot be empty.", s));

                if (command.equals("todo")) {
                    tasks.add(new TodoTask(s.substring(command.length() + 1)));
                } else if (command.equals("deadline")) {
                    if (!s.contains("/by"))
                        throw new EchoException("Please specify the deadline of the task. E.g. deadline return book /by Sunday");
                    desc = s.split("/");
                    tasks.add(new DeadlineTask(desc[0].substring(command.length() + 1), desc[1].substring(desc[1].indexOf(" ") + 1)));
                } else {
                    if (!s.contains("/at"))
                        throw new EchoException("Please specify the time of the event. E.g. event meeting /at Mon");
                    desc = s.split("/");
                    tasks.add(new EventTask(desc[0].substring(command.length() + 1), desc[1].substring(desc[1].indexOf(" ") + 1)));
                }
                System.out.println("        Got it. I've added this task:");
                taskStatus(tasks.size() - 1);
                System.out.printf("        Now you have %d tasks in the list.%n%n", tasks.size());
                break;
            default:
                System.out.println("       Commands:");
                System.out.println("       List                                | List current tasks.");
                System.out.println("       todo <description>                  | Adds a todo task.");
                System.out.println("       deadline <description> /by <time>   | Adds a deadline task to be done before a specified time.");
                System.out.println("       event <description> /at <time>      | Adds an event task that occurs at a specified time.");
                System.out.println("       mark <task number>                  | Marks task as completed.");
                System.out.println("       unmark <task number>                | Unmark task as uncompleted.");
                System.out.println("       delete <task number>                | Deletes task.");
                System.out.println("       bye                                 | exit.");
                break;

        }
    }
    /**
     * Prints string representation of task index and description.
     *
     * @param i Task index.
     */
    private void taskStatus(int i) {
        System.out.printf("          %d. %s%n", i+1, tasks.get(i).toString());
    }

    /**
     * Prints goodbye.
     */
    public void exit() {
        System.out.println("        Goodbye!");
    }
}
