package duke;

/**
 * Class responsible for executing the command from the userInput.
 */
public class Command {

    private static String listStatus(TaskList listOfTasks) {
        return "Now you have " + listOfTasks.size() + " tasks in the list.\n";
    }

    private static String taskAddedMessage(Task newTask, TaskList listOfTasks) {
        StringBuilder toPrint = new StringBuilder("Got it. I've added this task:\n");
        toPrint.append(newTask.toString()).append("\n");
        toPrint.append(listStatus(listOfTasks));
        return toPrint.toString();
    }

    private static String addTodo(String task, TaskList listOfTasks) throws DukeException {
        Task newTask = new ToDos(task);
        listOfTasks.add(newTask);
        return taskAddedMessage(newTask, listOfTasks);
    }

    private static String addDeadline(String task, String deadline, TaskList listOfTasks) throws DukeException {
        Task newTask = new Deadlines(task, deadline);
        listOfTasks.add(newTask);
        return taskAddedMessage(newTask, listOfTasks);
    }

    private static String addEvent(String task, String timing, TaskList listOfTasks) throws DukeException {
        Task newTask = new Event(task, timing);
        listOfTasks.add(newTask);
        return taskAddedMessage(newTask, listOfTasks);
    }

    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int formatNumber(String userInput) throws DukeException {
        if (!isInteger(userInput)) {
            throw new DukeException("☹ OOPS!!! Please enter a appropriate integer\n");
        }
        return Integer.parseInt(userInput) - 1;
    }

    private static String deleteTask(String userInput, TaskList listOfTasks) throws DukeException {
        int index = formatNumber(userInput);

        if (index < 0 || index > listOfTasks.size() - 1) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the number you provided is invalid\n");
        } else {
            Task removedTask = listOfTasks.remove(index);
            StringBuilder toPrint = new StringBuilder("Noted. I've removed this task:\n");
            toPrint.append(removedTask.toString()).append("\n");
            toPrint.append(listStatus(listOfTasks));
            return toPrint.toString();
        }
    }

    private static String listTasks(TaskList listOfTasks) {
        StringBuilder toPrint = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            int num = i + 1;
            String toAdd = num + "." + listOfTasks.get(i) + "\n";
            toPrint.append(toAdd);
        }
        return toPrint.toString();
    }

    private static String markTask(String userInput, TaskList listOfTasks) throws DukeException {
        int index = formatNumber(userInput);
        if (index < 0 || index > listOfTasks.size() - 1) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the number you provided is invalid\n");
        }
        Task toMark = listOfTasks.get(index);
        toMark.mark();
        StringBuilder toPrint = new StringBuilder("Nice! I've marked this task as done:\n").append(toMark)
                                                                                           .append("\n");
        return toPrint.toString();
    }

    private static String unmarkTask(String userInput, TaskList listOfTasks) throws DukeException {
        int index = formatNumber(userInput);
        if (index < 0 || index > listOfTasks.size() - 1) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the number you provided is invalid\n");
        }
        Task toUnmark = listOfTasks.get(index);
        toUnmark.unmark();
        StringBuilder toPrint = new StringBuilder("OK, I've marked this task as not done yet:\n")
                .append(toUnmark)
                .append("\n");
        return toPrint.toString();
    }

    private static String findTask(String keyword, TaskList listOfTasks) {
        StringBuilder toPrint = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : listOfTasks.getList()) {
            if (task.describe().contains(keyword)) {
                String toAdd = count + "." + task + "\n";
                toPrint.append(toAdd);
                count++;
            }
        }
        return toPrint.toString();
    }

    /**
     * Contains the logic for processing the processed userInput, finds the corresponding commands
     * to the userInput and executes it.
     *
     * @param processedInput String array containing valuable information regarding the command
     *                       to execute
     * @param listOfTasks TaskList containing the current list of task
     * @return String containing any responses to the user after execution of command
     */
    public static String execute(String[] processedInput, TaskList listOfTasks) {
        String toPrint = "";
        try {
            switch (processedInput[0]) {
            case "list":
                toPrint = listTasks(listOfTasks);
                break;
            case "unmark":
                toPrint = unmarkTask(processedInput[1], listOfTasks);
                break;
            case "mark":
                toPrint = markTask(processedInput[1], listOfTasks);
                break;
            case "delete":
                toPrint = deleteTask(processedInput[1], listOfTasks);
                break;
            case "deadline":
                toPrint = addDeadline(processedInput[1], processedInput[2], listOfTasks);
                break;
            case "event":
                toPrint = addEvent(processedInput[1], processedInput[2], listOfTasks);
                break;
            case "todo":
                toPrint = addTodo(processedInput[1], listOfTasks);
                break;
            case "find":
                toPrint = findTask(processedInput[1], listOfTasks);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(\n");
            }
        } catch (DukeException e) {
            toPrint = e.getMessage();
        }
        return toPrint;
    }

}
