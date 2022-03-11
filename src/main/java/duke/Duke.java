package duke;
import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;
/** 
 * The main program where everthing starts
 * */

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public Duke() {
        storage = new Storage("data/saved_task.txt");
        taskList = new TaskList(storage.fetchTask());
    }

    /**
     * 
     * @param input the input from the user
     * @return the response by the program
     * @throws DukeException 
     */

    public String getResponse(String input) throws DukeException {
        try {
            return runCommand(input);
        }
         catch (DukeException e) {
            return e.getMessage();
        }

    }

    /**
     * 
     * @param input the input from the user
     * @throws DukeException
     */

    public String runCommand(String input) throws DukeException {
        Ui.addNewLine();
        //assert false;

        String command = Parser.getCommand(input);
        String description = "";

        try {

            switch (command) {
            case "bye":
                storage.saveTask(taskList.getTaskList());
                return Ui.byeMessage();
            case "list":
                return Ui.printAllTasks(taskList.getTaskList());
            case "mark":
                description = Parser.getTaskDescription(input).trim();
                return taskList.markTask(description);
            case "unmark":
                description = Parser.getTaskDescription(input).trim();
                return taskList.unmarkTask(description);
            case "deadline":
                description = Parser.getTaskDescription(input).trim();
                return taskList.addDeadline(description);
            case "event":
                description = Parser.getTaskDescription(input).trim();
                return taskList.addEvent(description);
            case "todo":
                description = Parser.getTaskDescription(input).trim();
                return taskList.addTodo(description);
            case "delete":
                description = Parser.getTaskDescription(input).trim();
                return taskList.deleteTask(description);
            case "find":
                description = Parser.getTaskDescription(input).trim();
                return taskList.findTask(description, taskList.getTaskList());
            default:
                throw new DukeUnknownCommandException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

 
}
