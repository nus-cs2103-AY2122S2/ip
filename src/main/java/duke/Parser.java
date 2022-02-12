package duke;

import java.time.LocalDate;

/**
 * Executes user commands
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Parser
     *
     * @param ui the ui of Duke
     * @param taskList the tasklist to store all the tasks
     * @param storage the storage to save all the tasks
     */
    Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * if input starts with list, print out all tasks in the list
     * if input starts with mark, mark the task as done
     * if input starts with unmark, unmark the task as not done
     * if input starts with todo, deadline or event, create a corresponding task and add to list
     * if input starts with delete, delete the corresponding task
     * if input starts with check, check all the task on the same date
     *
     * @param input User command
     * @return String after the execution of user command
     * @throws DukeException If wrong input is detected
     */
    String execute(String input) throws DukeException {
        try {
            String[] inputArray = input.split(" ", 2);
            String commandType = inputArray[0].toLowerCase();
            switch (commandType) {
            case "bye":
                return ui.echo("GoodBye! Thanks for using B.H!");
            case "list":
                return ui.echo(this.taskList.getList());
            case "mark":
                int index = Integer.parseInt(inputArray[1]) - 1;
                if (index < this.taskList.getListSize()) {
                    return ui.echo("Well done! \n" + this.taskList.mark(index));
                } else {
                    return ui.echo("Index out of range");
                }
            case "unmark":
                int index = Integer.parseInt(inputArray[1]) - 1;
                return ui.echo("Oh no! \n" + this.taskList.unMark(index));
            case "todo":
                String task = inputArray[1];
                Task newTask = new Todo(task);
                this.taskList.addToList(newTask);
                return ui.echo("Task added: " + newTask.toString() + "\n"
                       + "Now you have " + this.taskList.getListSize() + " tasks in the list");
            case "deadline":
                String s = inputArray[1];
                String[] arr = s.split("/by");
                String task = arr[0];
                String time = arr[1];
                Task newTask = new Deadline(task, time);
                this.taskList.addToList(newTask);
                return ui.echo("Task added:" + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list");
            case "event":
                String s = inputArray[1];
                String[] arr = s.split("/at");
                String task = arr[0];
                String time = arr[1];
                Task newTask = new Event(task, time);
                this.taskList.addToList(newTask);
                return ui.echo("Task added:" + newTask.toString() + "\n"
                        + "Now you have " + this.taskList.getListSize() + " tasks in the list");
            case "delete":
                int index = Integer.parseInt(inputArray[1]) - 1;
                return ui.echo("Okay, I have remove this task:\n"
                        + this.taskList.deleteTask(index));
            case "check":
                LocalDate date = LocalDate.parse(inputArray[1]);
                return ui.echo(this.taskList.checkDate(date));
            case "find":
                String[] arr = input.split(" ", 2);
                String word = arr[1];
                return ui.echo(this.taskList.checkWord(word));
            default:
                return ui.echo("Wrong input, please check again");
            }
            this.storage.save(this.taskList.getArrayList());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException(ui.echo("Duke exception!!!"));
        }
    }
}
