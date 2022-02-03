package duke.task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the Duke Chatbot. A <code>Duke</code> object corresponds to a Duke chatbot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;


    /**
     * Creates a new instance of <code>Duke</code>> object and initializes its attributes.
     */
    public Duke() {
        ui = new Ui();

        ui.greeting();

        storage = new Storage();

        try {
            storage.checkFile();
        } catch (IOException ex) {
            ui.showLoadingError();
        }

        try {
            taskList = new TaskList(storage.readFile());
        } catch (LoadingException ex) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>(100));
        }

        parser = new Parser();
    }

    /**
     * Creates and runs a new <code>Duke</code> object.
     *
     * @param args an array of Strings.
     */
    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * Runs the <code>Duke</code> object, and saves its data after the run finishes.
     */
    public void run() {
        storage.saveFile(taskList.getToDoList());
    }

    /**
     * Reads the user input and responds based on it.
     * If the input matches none of the command, prompts the user to re-enter.
     */
    public String respond(String input) {
        int i = input.indexOf(' ');
        String command;
        String restOfInput = "";
        if (i > -1) {
            command = input.substring(0, i);
            restOfInput = input.substring(i).trim();
        } else {
            command = input;
        }

        switch (command) {
        case "bye":
            return ui.goodbye();
        case "list":
            return ui.displayList(taskList.getToDoList());
        case "mark":
        case "unmark":
            return mark(Integer.parseInt(restOfInput));
        case "todo":
        case "deadline":
        case "event":
            Task newTask;
            if (command.equals("todo")) {
                command = restOfInput;
                try {
                    newTask = makeToDo(command);
                } catch (ToDoIllegalArgumentException ex) {
                    return ui.showIllegalArgumentError(ex);
                }
            } else if (command.equals("deadline")) {
                try {
                    String[] s = parser.parseDeadline(restOfInput);
                    newTask = new Deadline(s[0], s[1]);
                } catch (IncompleteArgumentException ex) {
                    return ui.showIncompleteArgumentError();
                }
            } else {
                try {
                    String[] s = parser.parseEvent(restOfInput);
                    newTask = new Event(s[0], s[1]);
                } catch (IncompleteArgumentException ex) {
                    return ui.showIncompleteArgumentError();
                }
            }
            taskList.add(newTask);
            return ui.confirmAddition(newTask, taskList.getToDoList());
        case "delete":
            return remove(Integer.parseInt(restOfInput));
        case "find":
            ArrayList<Task> relevantTasks = taskList.find(restOfInput);
            return ui.showFindResult(relevantTasks);
        default:
            return ui.doNotUnderstand();
        }
    }

    /**
     * Reverses the boolean <code>done</code> attribute of the task at the given list index.
     *
     * @param idx Index of the task in the <code>taskList</code>;
     */
    public String mark(int idx) {
        taskList.get(idx - 1).mark();
        if (taskList.get(idx - 1).getIsDone()) {
            return ui.markAsDone(taskList.getToDoList(), idx);
        } else {
            return ui.unmarkAsDone(taskList.getToDoList(), idx);
        }
    }

    /**
     * Returns a <code>ToDo</code> with the specified name.
     *
     * @param name Name of the task.
     * @return new instance of <code>ToDo</code> with the specified name.
     * @throws ToDoIllegalArgumentException If the name is an empty string.
     */
    public Task makeToDo(String name) throws ToDoIllegalArgumentException {
        if (name.isEmpty()) {
            throw new ToDoIllegalArgumentException("Illegal Argument");
        }
        return new ToDo(name);
    }

    /**
     * Removes the <code>task</code> at the specified index of the <code>taskList</code>.
     *
     * @param idx Index of the <code>task</code> in the <code>taskList</code>.
     */
    public String remove(int idx) {
        Task removed = taskList.remove(idx - 1);
        return ui.confirmRemoval(removed, taskList.getToDoList());
    }

    String getResponse(String input) {
        return "Xzzzbot: " + respond(input);
    }

    String greeting() {
        return ui.greeting();
    }
}
