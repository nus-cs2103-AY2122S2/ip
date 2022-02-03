package Duke.main;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.task.ToDo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parser class is to parse user commands into strings and how to read the strings
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private String input;

    /**
     * Constructor of Parser class
     * @param storage where to load/read input data
     * @param taskList data to load/store
     * @param input user input
     */
    public Parser(Storage storage, TaskList taskList, String input) {
        this.storage = storage;
        this.tasks = taskList;
        this.input = input;
    }

    /**
     * to check whether an input contains integer
     * @param str a string of inputs
     * @return true if contains an integer, else false
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Parse the user command and action taken
     */
    public void userCommand() throws IOException {
        String inputArr[] = input.split(" ", 2);
        if (inputArr[0].equals("bye")) {
            Ui.byeMessage();
            System.exit(0);

        } else if (inputArr[0].equals("list")) {
            Ui.listMessage();
            for (int i = 1; i <= tasks.getTaskList().size(); i++) {
                System.out.println(i + "." + tasks.getTaskList().get(i - 1));
            }
            System.out.println(Ui.LINE);

        } else if (inputArr[0].equals("mark") && isInteger(inputArr[1])) {
            try {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                tasks.getTaskList().get(taskNum).markAsDone();
                Ui.markMessage();
                System.out.println(tasks.getTaskList().get(taskNum) + Ui.LINE);
                storage.writeToFile(tasks);
            } catch (IndexOutOfBoundsException exp) {
                Ui.markErrorMessage();
            }

        } else if (inputArr[0].equals("unmark") && isInteger(inputArr[1])) {
            try {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                tasks.getTaskList().get(taskNum).markAsNotDone();
                Ui.unmarkMessage();
                System.out.println(tasks.getTaskList().get(taskNum) + Ui.LINE);
                storage.writeToFile(tasks);
            } catch (IndexOutOfBoundsException exp) {
                Ui.markErrorMessage();
            }

        } else if (inputArr[0].equals("todo")) {
            ToDo t = new ToDo(inputArr[1]);
            tasks.addTodoTask(t);
            storage.writeToFile(tasks);

        } else if (inputArr[0].equals("deadline")) {
            try {
                String deadlineArr[] = inputArr[1].split("/by ", 2);
                Deadline d = new Deadline(deadlineArr[0], deadlineArr[1]);
                tasks.addDeadlineTask(d);
                storage.writeToFile(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.deadlineErrorMessage();
            } catch (DateTimeParseException e) {
                Ui.localDateErrorMessage();
            }

        } else if (inputArr[0].equals("event")) {
            try {
                String eventArr[] = inputArr[1].split("/at ", 2);
                Event e = new Event(eventArr[0], eventArr[1]);
                tasks.addEventTask(e);
                storage.writeToFile(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.eventErrorMessage();
            } catch (DateTimeParseException e) {
                Ui.localDateErrorMessage();
            }

        } else if (inputArr[0].equals("delete") && isInteger(inputArr[1])) {
            try {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                tasks.deleteTask(taskNum);
                storage.writeToFile(tasks);
            } catch (IndexOutOfBoundsException e) {
                Ui.markErrorMessage();
            }

        } else if (inputArr[0].equals("find")) {
            try {
                String str = inputArr[1];
                ArrayList<Task> foundTasks = new ArrayList<Task>();
                for (int i = 0; i < tasks.getTaskList().size(); i++) {
                    Task task = tasks.getTaskList().get(i);
                    if (task.getDescription().contains(str)) {
                        foundTasks.add(task);
                    }
                }
                if (foundTasks.size() == 0) {
                    Ui.notFoundMessage();
                } else {
                    Ui.findMessage();
                    for (int i = 1; i <= foundTasks.size(); i++) {
                        System.out.println(i + "." + foundTasks.get(i - 1));
                    }
                    System.out.println(Ui.LINE);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.findErrorMessage();
            }


        } else {
            Ui.generalErrorMessage();
        }
    }

}

