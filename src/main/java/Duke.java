import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private Duke(String dirPath, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, fileName);
        this.taskList = new TaskList();
        this.storage.loadData(this.taskList, this.ui);
    }

    private void run() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            String[] inputArr = userInput.split(" ", 2);
            String command = inputArr[0];
            String details = inputArr.length > 1 ? inputArr[1] : "";
            try {
                if (command.equals(ValidCommand.BYE.label)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    isExit = true;
                } else if (command.equals(ValidCommand.LIST.label)) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(taskList);
                } else if (command.equals(ValidCommand.MARK.label)) {
                    this.toggleTaskDone(ValidCommand.MARK, details);
                } else if (command.equals(ValidCommand.UNMARK.label)) {
                    this.toggleTaskDone(ValidCommand.UNMARK, details);
                } else if (command.equals(ValidCommand.TODO.label)) {
                    this.addTodo(details);
                } else if (command.equals(ValidCommand.DEADLINE.label)) {
                    this.addDeadline(details);
                } else if (command.equals(ValidCommand.EVENT.label)) {
                    this.addEvent(details);
                } else if (command.equals(ValidCommand.DELETE.label)) {
                    this.deleteTask(details);
                } else {
                    throw new IllegalArgumentException(
                            String.format("Sorry, the command '%s' is not supported.", command));
                }
            } catch (IllegalArgumentException | DukeException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }

    private int convertIndex(String indexString) throws DukeException {
        if (indexString.strip().equals("")) {
            throw new DukeException("Please specify a task.");
        }
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a task using its index in the task list.");
        }
        if (index < 1 || index > taskList.getLength()) {
            throw new DukeException("Please specify a valid task index.");
        }
        return index;
    }

    private void toggleTaskDone(ValidCommand cmd, String indexString) throws DukeException {
        int index = this.convertIndex(indexString);
        Task task = this.taskList.getTask(index);
        if (cmd == ValidCommand.MARK) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        System.out.println();
        this.storage.saveData(this.taskList);
    }

    private void addTaskHelper(Task task) {
        this.taskList.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
        this.storage.saveData(this.taskList);
    }

    private void addTodo(String details) throws DukeException{
        if (details.strip().equals("")) {
            throw new DukeException("Please enter a description for the todo task.");
        }
        Task task = new Todo(details);
        addTaskHelper(task);
    }

    private void addDeadline(String details) throws DukeException {
        String[] deadlineInputs = details.split(" /by ", 2);
        if (deadlineInputs.length == 1 || deadlineInputs[1].strip().equals("")
                || deadlineInputs[0].strip().equals("")) {
            throw new DukeException("Please specify a deadline task as:\n" +
                    "deadline [description] /by [date in yyyy-mm-dd format].");
        }
        try {
            LocalDate parsedDate = LocalDate.parse(deadlineInputs[1]);
            Task task = new Deadline(deadlineInputs[0], parsedDate);
            addTaskHelper(task);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify your date in the yyyy-mm-dd format");
        }

    }

    private void addEvent(String details) throws DukeException {
        String[] eventInputs = details.split(" /at ", 2);
        if (eventInputs.length == 1 || eventInputs[1].strip().equals("")
                || eventInputs[0].strip().equals("")) {
            throw new DukeException("Please specify an event task as:\n" +
                    "deadline [description] /by [date in yyyy-mm-dd format].");
        }
        try {
            LocalDate parsedDate = LocalDate.parse(eventInputs[1]);
            Task task = new Event(eventInputs[0], parsedDate);
            addTaskHelper(task);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify your date in the yyyy-mm-dd format");
        }
    }

    private void deleteTask(String indexString) throws DukeException {
        int index = this.convertIndex(indexString);
        Task task = this.taskList.getTask(index);
        this.taskList.deleteTask(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
        this.storage.saveData(this.taskList);
    }
}
