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
     * execute when user command is bye
     */
    public void byeCommand() {
        Ui.byeMessage();
        System.exit(0);
    }

    /**
     * execute when user command is list
     */
    public void listCommand() {
        String inputArr[] = input.split(" ", 2);
        Ui.listMessage();
        for (int i = 1; i <= tasks.getTaskList().size(); i++) {
            System.out.println(i + "." + tasks.getTaskList().get(i - 1));
        }
        System.out.println(Ui.LINE);
    }

    /**
     * execute when user command is mark
     */
    public void markCommand() {
        String inputArr[] = input.split(" ", 2);
        try {
            int taskNum = Integer.parseInt(inputArr[1]) - 1;
            tasks.getTaskList().get(taskNum).markAsDone();
            Ui.markMessage();
            System.out.println(tasks.getTaskList().get(taskNum) + "\n" + Ui.LINE);
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException exp) {
            Ui.markErrorMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * execute when user command is unmark
     */
    public void unmarkCommand() {
        String inputArr[] = input.split(" ", 2);
        try {
            int taskNum = Integer.parseInt(inputArr[1]) - 1;
            tasks.getTaskList().get(taskNum).markAsNotDone();
            Ui.unmarkMessage();
            System.out.println(tasks.getTaskList().get(taskNum) + "\n" + Ui.LINE);
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException | IOException exp) {
            Ui.markErrorMessage();
        }
    }
    /**
     * execute when user command is unmark
     */
    public void todoCommand() throws IOException {
        String inputArr[] = input.split(" ", 2);
        ToDo t = new ToDo(inputArr[1]);
        tasks.addTodoTask(t);
        storage.writeToFile(tasks);
    }

    /**
     * execute when user command is deadline
     */
    public void deadlineCommand() {
        String inputArr[] = input.split(" ", 2);
        try {
            String deadlineArr[] = inputArr[1].split("/by ", 2);
            Deadline d = new Deadline(deadlineArr[0], deadlineArr[1]);
            tasks.addDeadlineTask(d);
            storage.writeToFile(tasks);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            Ui.deadlineErrorMessage();
        } catch (DateTimeParseException e) {
            Ui.localDateErrorMessage();
        }
    }

    /**
     * execute when user command is Event
     */
    public void eventCommand() {
        String inputArr[] = input.split(" ", 2);
        try {
            String eventArr[] = inputArr[1].split("/at ", 2);
            Event e = new Event(eventArr[0], eventArr[1]);
            tasks.addEventTask(e);
            storage.writeToFile(tasks);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            Ui.eventErrorMessage();
        } catch (DateTimeParseException e) {
            Ui.localDateErrorMessage();
        }
    }

    /**
     * execute when user command is delete
     */
    public void deleteCommand() {
        String inputArr[] = input.split(" ", 2);
        try {
            int taskNum = Integer.parseInt(inputArr[1]) - 1;
            tasks.deleteTask(taskNum);
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException | IOException e) {
            Ui.markErrorMessage();
        }
    }

    /**
     * execute when user command is find
     */
    public void findCommand() {
        String inputArr[] = input.split(" ", 2);
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
                System.out.println("\n" + Ui.LINE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.findErrorMessage();
        }
    }

    /**
     * execute when user command is help
     */
    public void helpCommand() {
        System.out.println("------------Command help page---------------\n"
                + "To view all task available: list\n"
                + "To add task: todo (description) \n "
                + "             deadline (description)/YYYY-MM-DD\n"
                + "             event (description)/YYYY-MM-DD\n"
                + "To mark or unmark task:  mark/unmark taskNumber\n"
                + "To delete any task available: delete taskNumber\n"
                + "To find a task: find (keyword of the task looking for)\n");
    }

    /**
     * Parse the user command and action taken
     */
    public void userCommand() throws IOException {
        String inputArr[] = input.split(" ", 2);
        inputArr[0] = inputArr[0].toLowerCase();
        assert !inputArr[0].isEmpty() : "Please input your commend";
        if (inputArr[0].equals("bye")) {
            assert inputArr[0].length() == 3 : "command should be bye";
            byeCommand();
        } else if (inputArr[0].equals("list")) {
            assert inputArr[0].length() == 4 : "command should be list";
            listCommand();
        } else if (inputArr[0].equals("mark") && isInteger(inputArr[1])) {
            assert inputArr[0].length() == 4 : "command should be mark";
            markCommand();
        } else if (inputArr[0].equals("unmark") && isInteger(inputArr[1])) {
            assert inputArr[0].length() == 6 : "command should be unmark";
            unmarkCommand();
        } else if (inputArr[0].equals("todo")) {
            todoCommand();
        } else if (inputArr[0].equals("deadline")) {
            deadlineCommand();
        } else if (inputArr[0].equals("event")) {
            assert inputArr[0].length() == 5 : "command should be event";
            eventCommand();
        } else if (inputArr[0].equals("delete") && isInteger(inputArr[1])) {
            deleteCommand();
        } else if (inputArr[0].equals("find")) {
            findCommand();
        } else if (inputArr[0].equals("help")) {
            helpCommand();
        } else {
            Ui.generalErrorMessage();
        }
    }

}

