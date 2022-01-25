import java.util.Scanner;

/**
 * Bernie the bot that is the driver for the responses to the user.
 * Internally, Bernie has TaskList, which contain the Tasks that a user inputs
 */

public class Bernie {
    TaskList tasks;
    Ui ui;
    Storage storage;
    Parser parser;

    /**
     * Constructs a new Bot containing TaskList and greets the user
     */
    Bernie() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser(tasks);
        ui.greet();
    }

    Storage getStorage() {
        return storage;
    }

    /**
     * Displays to the user a message according to their input. Actions are performed according
     * to the input. Exceptions are caught and printed out for the user.
     * @param input String, user input
     * @return boolean value, indicating if the program will end or not.
     */
    boolean respond(String input) {
        Type type = Parser.parseType(input);
        try {
            switch (type) {
            case LIST:
                ui.showListTasksMsg(tasks);
                break;
            case BYE:
                ui.showLeaveMsg();
                break;
            case MARK:
                mark(input);
                break;
            case EMPTY:
                throw new BernieException("Say something???");
            case DELETE:
                delete(input);
                break;
            case ADD:
                add(input);
                break;
            default:
                break;
            }
        } catch (BernieException e) {
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg(e.getMessage());
        } finally {
            return input.equals("bye");
        }
    }

    /**
     * Bernie will decide what kind of task is to be created. Bernie splits the input accordingly,
     * to get the parameters required to create the type of task. The creation and adding of task will be
     * handled by the TaskList. New state of bernie.tasks is saved to the data directory
     * after we add a task.
     * @param input String, given by user. Bernie verifies the Task type and the Task either is todo,
     * deadline or event
     * @throws BernieException, if the task is not a valid type.
     */
    void add(String input) throws BernieException {
        Task newTask;
        if (Parser.isType(Type.TODO, input)) {
            String[] inputArr = Parser.getParams(Type.TODO, input);
            newTask = tasks.addTask(inputArr, "todo");
        } else if (Parser.isType(Type.DEADLINE, input)) {
            String[] inputArr = Parser.getParams(Type.DEADLINE, input);
            newTask = tasks.addTask(inputArr, "deadline");
        } else if (Parser.isType(Type.EVENT, input)) {
            String[] inputArr = Parser.getParams(Type.EVENT, input);
            newTask = tasks.addTask(inputArr, "event");
        } else {
            throw new BernieException("Not a valid type of task!");
        }
        storage.saveTasks(tasks);
        ui.showAddedMsg(newTask, tasks.numTasksLeft());
    }

    /**
     * Delete the task according to the user input. The new state of the
     * bernie.tasks is saved to the data directory after deletion of task
     * @param input String, which is of the form delete ___
     */
    void delete(String input) {
        try {
            String taskNum = Parser.getParams(Type.DELETE, input)[0];
            Task deletedTask = tasks.deleteTask(taskNum);
            storage.saveTasks(tasks);
            ui.showDeleteMsg(deletedTask, tasks.numTasksLeft());
        } catch (BernieException e){
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg(e.getMessage());
        }
    }

    /**
     * Mark or unmark a task number depending on the input. Handles error if user
     * enters invalid mark inputs etc.
     * @param input String, containing mark/unmark and the task number to perform action
     */
    void mark(String input) {
        try {
            String[] inputArr = Parser.getParams(Type.MARK, input);
            String action = inputArr[0];
            String taskNum = inputArr[1];
            if (action.equals("mark")) {
                Task markedTask = tasks.markTask(Type.MARK, taskNum);
                ui.showDoneMsg(markedTask);
            } else if (action.equals("unmark")) {
                Task unmarkedTask = tasks.markTask(Type.UNMARK, taskNum);
                ui.showUndoneMsg(unmarkedTask);
            }
        } catch (BernieException e) {
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bernie bernie = new Bernie();
        Storage storage = bernie.getStorage();
        storage.loadTasks();
        while (true) {
            String input = sc.nextLine();
            boolean end = bernie.respond(input);
            if (end) {
                break;
            }
        }
    }
}
