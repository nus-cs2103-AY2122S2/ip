import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Returns a styled message with front and back styling
     *
     * @param msg the message to be styled
     * @return    the styled message
     */
    public static String formatMsg(String msg) {
        String startFormat = "####################\n";
        String endFormat = "####################";
        return startFormat + msg + endFormat;
    }

    /**
     * Returns a styled welcome message upon launch
     */
    public static void displayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(formatMsg(welcomeMsg));
    }

    /**
     * Returns a styled exit message on exit
     */
    public static void displayExitMsg() {
        System.out.println(formatMsg("Bye. Hope to see you again soon!\n"));
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of Task data
     * @return     data as a list in a single string
     */
    public static String renderTaskList(ArrayList<Task> data) {
        String renderStr = "";

        for (int i = 0; i < data.size(); i++) {
            String dataStr = String.format("%d. ", i+1)
                    + data.get(i)
                    + " \n";
            renderStr += dataStr;
        }

        return renderStr;
    }

    public static void displayMarkMsg(String task) {
        String markMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        System.out.println(formatMsg(markMsg));
    }

    public static void displayUnmarkMsg(String task) {
        String unmarkMsg = "Nice! I've marked this task as NOT done:\n"
                + task + "\n";
        System.out.println(formatMsg(unmarkMsg));
    }

    public static void displayListedText(Task task, int size) {
        String output = " Got it. I've added this task:\n   "
                + task.toString()
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        System.out.println(formatMsg(output));
    }

    public static LocalDateTime[] getEventTimings(String text) {
        // /at 2/12/2022 1800 to 1900
        String dateText = text.split("/at")[1].trim().split("to")[0].trim().split(" ")[0];
        String startTimeText = text.split("/at")[1].trim().split("to")[0].trim().split(" ")[1];
        String endTimeText = text.split("/at")[1].trim().split("to")[1].trim();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String startDateText = dateText + " " + startTimeText;
        String endDateText = dateText + " " + endTimeText;
        LocalDateTime startDate = LocalDateTime.parse(startDateText, format);
        LocalDateTime endDate = LocalDateTime.parse(endDateText, format);
        LocalDateTime[] eventTimings = new LocalDateTime[] {startDate, endDate};
        return eventTimings;
    }

    public static LocalDateTime getDeadlineTiming(String text) {
        String dateText = text.split("/by")[1].trim();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(dateText, format);
        return date;
    }

    public static void displayDeletedMessage(Task deletedTask, int size) {
        String output = " Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        System.out.println(formatMsg(output));
    }

    /**
     * Runs Level 5 & 6 version of the app, Exception handling
     *
     * @throws DukeException for checked errors handled by Duke app
     */
    public static void levelFinal() throws DukeException {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine().trim();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                displayExitMsg();
                return;

            } else if (command.equals("list")) {
                System.out.println(formatMsg(renderTaskList(data)));
                continue;

            } else if (command.contains("mark")) {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);

                if (itemIndex <= data.size()) {
                    Task selectedTask = data.get(itemIndex - 1);
                    if (command.contains("unmark")) {
                        selectedTask.markAsIncomplete();
                        displayUnmarkMsg(selectedTask.toString());
                    } else {
                        selectedTask.markAsComplete();
                        displayMarkMsg(selectedTask.toString());
                    }
                }
                continue;

            } else if (command.equals("deadline")) {
                LocalDateTime endTime = getDeadlineTiming(input);
                String taskName = input.replaceAll("deadline", "").split("/by")[0];
                Deadline newDeadline = new Deadline(taskName, endTime);
                data.add(newDeadline);
                displayListedText(newDeadline, data.size());

            } else if (command.equals("event")) {
                LocalDateTime[] eventTimings = getEventTimings(input);
                String taskName = input.replaceAll("event", "").split("/at")[0];
                Event newEvent = new Event(taskName, eventTimings[0], eventTimings[1]);
                data.add(newEvent);
                displayListedText(newEvent, data.size());

            } else if (command.equals("todo")) {
                String taskName = input.replaceAll("todo", "");

                if (input.split(" ").length <= 1) {
                    throw new TodoEmptyException();
                }

                Todo newTodo = new Todo(taskName);
                data.add(newTodo);
                displayListedText(newTodo, data.size());

            } else if (command.equals("delete")) {
                if (input.split("").length <= 1) {
                    throw new DeleteEmptyException();
                }

                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                try {
                    Task deletedTask = data.remove(taskIndex - 1);
                    if (deletedTask.getEventType() == Type.EVENT) {
                        Event deletedEvent = (Event) deletedTask;
                        displayDeletedMessage(deletedEvent, data.size());
                    } else if (deletedTask.getEventType() == Type.DEADLINE) {
                        Deadline deletedDeadline = (Deadline) deletedTask;
                        displayDeletedMessage(deletedDeadline, data.size());
                    } else if (deletedTask.getEventType() == Type.TODO) {
                        Todo deletedTodo = (Todo) deletedTask;
                        displayDeletedMessage(deletedTodo, data.size());
                    }
                } catch (IndexOutOfBoundsException err) {
                    throw new DukeException("task index provided is invalid :(");
                }

            } else {
                throw new UnknownCommandException();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        levelFinal();
    }
}
