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
     * @throws EchoException If input is missing details or badly formatted.
     */
    public void read(String s) throws EchoException {
        String[] split = s.split(" ");
        String command = split[0];
        StringBuilder message = new StringBuilder();
        switch (command) {
        case "list":
            if (tasks.size() == 0) {
                printFormat("        Task list is empty!");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    message.append(String.format("        %d. %s \n", i + 1, tasks.get(i)));
                }
                message.setLength(message.length() - 2); // remove last /n
                printFormat(message.toString());
            }
            break;
        case "mark":
        case "unmark":
        case "delete":
            if (split.length == 1) {
                throw new EchoException("Please specify the task number. Eg. mark 1, unmark 1, delete 1");
            }
            try {
                int taskIndex = Integer.parseInt(split[1]) - 1;
                if (command.equals("mark")) {
                    tasks.get(taskIndex).mark();
                    message.append("        Nice! The task is marked as done: \n");
                } else if (command.equals("unmark")) {
                    tasks.get(taskIndex).unmark();
                    message.append("        OK! The task is unmarked. \n");
                } else {
                    message.append("        Noted. I've removed the task: \n");
                    message.append(String.format("          %d. %s \n", taskIndex + 1, tasks.get(taskIndex).toString()));
                    tasks.remove(taskIndex);
                }
                if (command.equals("delete")) {
                    message.append(String.format("        Now you have %d tasks in the list.", tasks.size()));
                } else {
                    message.append(taskStatus(taskIndex));
                }
                printFormat(message.toString());
            } catch (NumberFormatException nfe) {
                printFormat("        Second input must be an integer. Eg. mark 1, unmark 1, delete 1");
            } catch (ArrayIndexOutOfBoundsException e) {
                printFormat("        Task does not exist!");
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            String[] desc;
            if (split.length == 1) {
                throw new EchoException(String.format("        The description of a %s cannot be empty.", s));
            } else if (command.equals("todo")) {
                tasks.add(new TodoTask(s.substring(command.length() + 1)));
            } else if (command.equals("deadline")) {
                if (!s.contains("/by")) {
                    throw new EchoException("        Please specify the deadline of the task. E.g. deadline return book /by Sunday");
                }
                desc = s.split("/");
                tasks.add(new DeadlineTask(desc[0].substring(command.length() + 1), desc[1].substring(desc[1].indexOf(" ") + 1)));
            } else {
                if (!s.contains("/at")) {
                    throw new EchoException("        Please specify the time of the event. E.g. event meeting /at Mon");
                }
                desc = s.split("/");
                tasks.add(new EventTask(desc[0].substring(command.length() + 1), desc[1].substring(desc[1].indexOf(" ") + 1)));
            }
            message.append("        Got it. I've added this task: \n");
            message.append(taskStatus(tasks.size() - 1)).append("\n");
            message.append(String.format("        Now you have %d tasks in the list.", tasks.size()));
            printFormat(message.toString());
            break;
        default:
            commands();
            break;

        }
    }
    /**
     * Prints string representation of task index and description.
     *
     * @param i Task index.
     *
     * @return String representing task status.
     */
    private String taskStatus(int i) {
        return String.format("          %d. %s", i+1, tasks.get(i).toString());
    }

    /**
     * Prints hello.
     */
    public void hello() {
        String logo = "U _____ u    ____    _   _      U  ___ u \n" +
                "\\| ___\"|/ U /\"___|  |'| |'|      \\/\"_ \\/ \n" +
                " |  _|\"   \\| | u   /| |_| |\\     | | | | \n" +
                " | |___    | |/__  U|  _  |u .-,_| |_| | \n" +
                " |_____|    \\____|  |_| |_|   \\_)-\\___/  \n" +
                " <<   >>   _// \\\\   //   \\\\        \\\\    \n" +
                "(__) (__) (__)(__) (_\") (\"_)      (__) \n";
        System.out.print(logo);
    }

    /**
     * Prints goodbye.
     */
    public void exit() {
        printFormat("        Goodbye!");
    }

    /**
     * Prints response with line dividers.
     *
     * @param s String to be printed.
     */
    public void printFormat(String s) {
        System.out.println("        ____________________________________________________________");
        System.out.println(s);
        System.out.println("        ____________________________________________________________");
    }

    /**
     * Prints commands.
     */
    private void commands() {
        printFormat("        Commands: \n" +
                "        List                                | List current tasks. \n" +
                "        todo <description>                  | Adds a todo task. \n" +
                "        deadline <description> /by <time>   | Adds a deadline task to be done before a specified time. \n" +
                "        event <description> /at <time>      | Adds an event task that occurs at a specified time. \n" +
                "        mark <task number>                  | Marks task as completed. \n" +
                "        unmark <task number>                | Unmark task as uncompleted. \n" +
                "        delete <task number>                | Deletes task. \n" +
                "        bye                                 | exit. ");
    }


}
