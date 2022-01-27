package arthur;

/**
 * Handles decoding of the user commands
 */
public class Parser {
    private String command;
    private boolean isEnd;

    /**
     * Constructor for the parser object
     * @param inst String instruction to be followed
     */
    public Parser(String inst) {
        this.command = inst;
    }

    /**
     * Uses the command string to determine appropriate action.
     * If input is "list", will execute listOut method. If not, adds
     * instruction to tasks list.
     * @param taskList The tasklist of tasks and operations
     * @param storage The object to access data file in storage
     * @param ui The arthur.Ui class to use for user interaction
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String[] temp = this.command.split(" ", 2);    // Gets the first word
        String inst = temp[0];

        try {
            switch (inst) {
            case "list":
                this.command = taskList.listOut();
                break;
            case "mark":
            case "unmark":
                this.command = taskList.marker(this.command);
                storage.editTasks(taskList.getTask(Integer.parseInt(temp[1]) - 1), 1);
                break;
            case "todo":
                this.command = taskList.todo(temp[1]);
                storage.addTasks(taskList.getTask(taskList.tasksSize() - 1));
                break;
            case "deadline":
                this.command = taskList.deadline(temp[1]);
                storage.addTasks(taskList.getTask(taskList.tasksSize() - 1));
                break;
            case "event":
                this.command = taskList.event(temp[1]);
                storage.addTasks(taskList.getTask(taskList.tasksSize() - 1));
                break;
            case "delete":
                storage.editTasks(taskList.getTask(Integer.parseInt(temp[1]) - 1), 2);
                this.command = taskList.deleter(Integer.parseInt(temp[1]));
                break;
            case "find":
                this.command = taskList.find(temp[1]);
                break;
            case "bye":
                this.isEnd = true;
                this.command = "Bye!" + "\n" + "Have a great day!";
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            this.command = "Please enter a valid number!";
        }
        ui.printFormat(this.command);
    }

    /**
     * Uses boolean to show if "bye" command has been received.
     * @return True: If "bye" command given. False otherwise.
     */
    public boolean isEnd() {
        return this.isEnd;
    }
}
