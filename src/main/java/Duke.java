import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String divider = "\n______________________________________\n";

    public static void addTask(Task task) {
        System.out.println("Added as per your request: " + task);
        tasks.add(task);
        System.out.println("You now have a total of " + tasks.size() + " tasks in your list! Subarashii!");
    }

    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("Empty much!");
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        System.out.println(listOfTasks);
    }

    public static void markTask(int taskId, boolean mark) throws DukeException {
        Task toSet = tasks.get(taskId - 1);
        if (mark) {
            toSet.markIsDone();
            tasks.set(taskId - 1, toSet);
            System.out.println("Sugoi! I have marked this task as done!\n" + tasks.get(taskId - 1).toString());
        } else {
            if (toSet.isDone) {
                toSet.markUndone();
                tasks.set(taskId - 1, toSet);
                System.out.println("Daijoubu! I have unmarked this task for you!\n" + tasks.get(taskId - 1).toString());
            } else {
                throw new DukeException("This task has yet to be done!");
            }
        }
    }

    public static void main(String[] args) throws DukeException {


        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Konnichiwassup from\n" + logo);
        Scanner scanner = new Scanner(System.in);


        System.out.println(divider);
        System.out.println("What do you need help with?\n");
        String[] inputArray = scanner.nextLine().split(" ");


        while (!(inputArray[0].equalsIgnoreCase("bye") && inputArray.length == 1)) {

            // single command
            if (inputArray.length == 1) {
                if (inputArray[0].equalsIgnoreCase("list")) {
                    listTasks();
                } else {
                    throw new DukeException("Sumimasen! I don't recognize this command. Please try again!");
                }
            }

            // multi command
            if (inputArray.length > 1) {
                String taskType = inputArray[0].toLowerCase(Locale.ROOT);
                StringBuilder taskDetails = new StringBuilder();
                for (int i = 1; i < inputArray.length; i++) {
                    if (i != inputArray.length - 1) {
                        taskDetails.append(inputArray[i]).append(" ");
                    } else {
                        taskDetails.append(inputArray[i]);
                    }
                }

                String description = "";
                String date = "";
                String dateTime = "";

                if (taskDetails.toString().contains("/by")) {
                    description = taskDetails.toString().split("/by", 2)[0];
                    date = taskDetails.toString().split("/by", 2)[1];
                } else if (taskDetails.toString().contains("/at")) {
                    description = taskDetails.toString().split("/at", 2)[0];
                    dateTime = taskDetails.toString().split("/at", 2)[1];
                }

                switch (taskType) {
                    case "unmark":
                        try {
                            int taskId = Integer.parseInt(inputArray[1]);
                            if (taskId > 0 && taskId < (tasks.size() + 1)) {
                                markTask(taskId, false);
                            } else {
                                throw new DukeException("Task cannot be found within the task list! Please fix your machigai!");
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("Task ID has to be an integer!");
                        }
                        break;
                    case "mark":
                        try {
                            int taskId = Integer.parseInt(inputArray[1]);
                            if (taskId > 0 && taskId < (tasks.size() + 1)) {
                                markTask(taskId, true);
                            } else {
                                throw new DukeException("Task cannot be found within the task list! Please fix your machigai!");
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("Task ID has to be an integer!");
                        }
                        break;
                    case "todo":
                        Task ToDo = new Todo(taskDetails.toString());
                        if (taskDetails.toString().equals("")) {
                            throw new DukeException("Todo command is invalid!");
                        }
                        addTask(ToDo);
                        break;
                    case "deadline":
                        Task Deadline = new Deadline(description, date);
                        if (description.equals("") || date.equals("")) {
                            throw new DukeException("Deadline command is invalid!");
                        }
                        addTask(Deadline);
                        break;
                    case "event":
                        Task Event = new Event(description, dateTime);
                        if (dateTime.equals("") || description.equals("")) {
                            throw new DukeException("Event command is invalid");
                        }
                        addTask(Event);
                        break;
                    default:
                        throw new DukeException("Sumimasen! I don't recognize this command. Please try again!");
                }
            }
            System.out.println(divider);
            inputArray = scanner.nextLine().split(" ");
        }

        System.out.println(divider);
        System.out.println("Sayonara! Hope to see you again soon!");
        scanner.close();
    }

}

