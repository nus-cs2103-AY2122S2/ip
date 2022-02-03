package juke;

import juke.command.CommandHandler;
import juke.common.Storage;
import juke.common.TaskList;
import juke.common.Ui;

/**
 * Entry point for the Juke application.
 */
public class Juke {
    private static final Juke INSTANCE = new Juke();
    
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private boolean hasExited;
    
    /**
     * Constructor that initializes the application.
     */
    public Juke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(this);
        this.hasExited = false;
        CommandHandler.registerCommands();
    }
    
    private void run() {
        this.ui.greet();
        this.storage.loadTasks();
        while (!this.hasExited) {
            this.ui.runUiLoop();
        }
    }
    
    /**
     * Begins the process of exiting Juke.
     */
    public void exit() {
        this.hasExited = true;
    }
    /*
    private void echo(String[] args) {
        PrintHelper.getInstance().formattedPrint(String.join(" ", args));
    }
    
    private void addTodoToTaskList(String[] args) throws JukeException {
        if (args.length > 1) {
            if (this.taskList.size() < this.taskListSize) {
                String text = "";
                for (int i = 1; i < args.length; i++) {
                    text += args[i] + " ";
                }
                text = text.strip();
                this.taskList.add(new Todo(text));
                PrintHelper.getInstance().formattedPrint("Todo added: " + text);
            } else {
                throw new JukeTaskListFullException("todo");
            }
        } else {
            throw new JukeInvalidArgumentCountException("todo", 2, args.length);
        }
    }
    
    private void addDeadlineToTaskList(String[] args) throws JukeException {
        if (args.length > 3) {
            boolean isValid = false;
            int argIdx = 0;
            for (int j = 2; j < args.length - 1; j++) {
                if (args[j].equals("-by")) {
                    isValid = true;
                    argIdx = j;
                    break;
                }
            }
            if (isValid) {
                if (this.taskList.size() < this.taskListSize) {
                    String text = "";
                    for (int i = 1; i < argIdx; i++) {
                        text += args[i] + " ";
                    }
                    text = text.strip();
                    String time = "";
                    for (int i = argIdx + 1; i < args.length; i++) {
                        time += args[i] + " ";
                    }
                    time = time.strip();
                    this.taskList.add(new Deadline(text, time));
                    PrintHelper.getInstance().formattedPrint("Deadline added: " + text);
                } else {
                    throw new JukeTaskListFullException("deadline");
                }
            } else {
                throw new JukeMissingArgumentException("deadline", "-by");
            }
        } else {
            throw new JukeInvalidArgumentCountException("deadline", 4, args.length);
        }
    }
    
    private void addEventToTaskList(String[] args) throws JukeException {
        if (args.length > 3) {
            boolean isValid = false;
            int argIdx = 0;
            for (int j = 2; j < args.length - 1; j++) {
                if (args[j].equals("-at")) {
                    isValid = true;
                    argIdx = j;
                    break;
                }
            }
            if (isValid) {
                if (this.taskList.size() < this.taskListSize) {
                    String text = "";
                    for (int i = 1; i < argIdx; i++) {
                        text += args[i] + " ";
                    }
                    text = text.strip();
                    String time = "";
                    for (int i = argIdx + 1; i < args.length; i++) {
                        time += args[i] + " ";
                    }
                    time = time.strip();
                    this.taskList.add(new Event(text, time));
                    PrintHelper.getInstance().formattedPrint("Event added: " + text);
                } else {
                    throw new JukeTaskListFullException("event");
                }
            } else {
                throw new JukeMissingArgumentException("event", "-at");
            }
        } else {
            throw new JukeInvalidArgumentCountException("event", 4, args.length);
        }
    }
    
    private void find(String[] args) throws JukeException {
        if (args.length > 1) {
            String query = "";
            for (int i = 1; i < args.length; i++) {
                query += args[i] + " ";
            }
            query = query.strip();
            List<Task> results = this.taskList.search(query);
            if (results.size() == 0) {
                throw new JukeException("No results found.");
            }
            String[] descriptions = new String[results.size()];
            for (int i = 0; i < results.size(); i++) {
                descriptions[i] = results.get(i).toString();
            }
            this.ui.formattedPrint(descriptions);
        } else {
            throw new JukeInvalidArgumentCountException("find", 2, args.length);
        }
    }
    
    private void displayTaskList() throws JukeException {
        if (this.taskList.size() > 0) {
            String str = "";
            for (int i = 0; i < this.taskList.size(); i++) {
                str += (i + 1) + ". " + this.taskList.get(i) + "\n";
            }
            PrintHelper.getInstance().formattedPrint(str.stripTrailing());
        } else {
            throw new JukeTaskListEmptyException("list");
        }
    }
    
    private void markTaskAsDone(String[] args) throws JukeException {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.taskList.size()) {
                    throw new JukeInvalidArgumentException("mark", "<juke.task index>", args[1]);
                }
                String des = this.taskList.get(idx - 1).getDescription();
                this.taskList.get(idx - 1).markAsDone();
                PrintHelper.getInstance().formattedPrint("Marked task \u00ab" + des + "\u00bb as done.");
            } catch (NumberFormatException e) {
                throw new JukeInvalidArgumentException("mark", "<juke.task index>", args[1]);
            }
        } else {
            throw new JukeInvalidArgumentCountException("mark", 2, args.length);
        }
    }
    
    private void markTaskAsNotDone(String[] args) throws JukeException {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.taskList.size()) {
                    throw new JukeInvalidArgumentException("unmark", "<juke.task index>", args[1]);
                }
                String des = this.taskList.get(idx - 1).getDescription();
                this.taskList.get(idx - 1).markAsNotDone();
                PrintHelper.getInstance().formattedPrint("Marked task \u00ab" + des + "\u00bb as not done.");
            } catch (NumberFormatException e) {
                throw new JukeInvalidArgumentException("unmark", "<juke.task index>", args[1]);
            }
        } else {
            throw new JukeInvalidArgumentCountException("unmark", 2, args.length);
        }
    }
    
    private void deleteTaskFromTaskList(String[] args) throws JukeException {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.taskList.size()) {
                    throw new JukeInvalidArgumentException("delete", "<juke.task index>", args[1]);
                }
                String des = this.taskList.get(idx - 1).getDescription();
                this.taskList.remove(idx - 1);
                PrintHelper.getInstance().formattedPrint("Removed task \u00ab" + des + "\u00bb from task list.");
            } catch (NumberFormatException e) {
                throw new JukeInvalidArgumentException("delete", "<juke.task index>", args[1]);
            }
        } else {
            throw new JukeInvalidArgumentCountException("delete", 2, args.length);
        }
    }
    */
    
    /**
     * Returns the list used to store tasks.
     *
     * @return TaskList.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
    
    /**
     * Returns the UI class used to handle inputs and outputs.
     *
     * @return Ui.
     */
    public Ui getUi() {
        return this.ui;
    }
    
    /**
     * Returns the storage class used to handle file storage.
     *
     * @return Storage.
     */
    public Storage getStorage() {
        return this.storage;
    }
    
    /**
     * Returns the singleton instance of Juke.
     *
     * @return Juke.
     */
    public static Juke getInstance() {
        return INSTANCE;
    }
    
    /**
     * Main class for the Juke application.
     *
     * @param args Running arguments, not used.
     */
    public static void main(String[] args) {
        INSTANCE.run();
    }
}
