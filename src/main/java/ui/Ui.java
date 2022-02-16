package ui;

import exception.DukeException;
import notes.NoteList;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * Interface that the user interacts with.
 * Involves a scanner to read user inputs.
 */
public class Ui {

    private TaskList tasks;
    private NoteList notes;

    /**
     * Ui class constructor.
     * @param tasks contains list of all tasks added by user.
     * @param notes contains list of all notes added by user.
     */
    public Ui(TaskList tasks, NoteList notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

    /**
     * Starts conversation with user by combining Parser and Storage.
     * @param parser Instance of Parser to process user commands.
     * @param storage Instance of storage to update data in storage
     * @param userInput String input entered by the user.
     * @throws DukeException If invalid input message is entered.
     */
    public String startConversation(Parser parser, Storage storage, String userInput) throws DukeException {
        String[] inputStringArray = userInput.split(" ");
        if (userInput.equals("bye")) {
            System.exit(0);
        }
        return parser.userCommand(inputStringArray, storage);
    }

    /**
     * Displays a user greeting.
     * @return String message to greet user when the bot is started.
     */
    public static String greetUser() {
        return "Hello! I'm Duke :) \nWhat can I do for you? :D\n\n"
                + "Enter 'help' to view Duke commands...";
    }

    /**
     * Provides help to user by listing duke commands.
     * @return String containing all duke commands.
     */
    public static String userHelp() {
        return "Duke commands:\n"
                + "To access notes, start command with 'note'. To access tasks start command with 'task'.\n\n"
                + "Task feature example commands:\n"
                + "1. Creating todos: task new todo <todo description>\n"
                + "2. Creating deadlines: task new deadline <deadline description> by <date in YYYY-MM-DD format>\n"
                + "3. Creating events: task new event <event description> on <date in YYYY-MM-DD format>\n"
                + "4. Viewing task list: task list\n"
                + "5. Marking tasks as complete: task mark <index number of task to mark>\n"
                + "6. Marking tasks as incomplete: task unmark: <index number of task to unmark>\n"
                + "7. Find a tasks by keyword: task find <keyword>\n"
                + "8. Deleting a task by index number: task delete <index number>\n"
                + "9. Deleting all tasks: task delete all\n\n"
                + "Note feature example commands:\n"
                + "1. Creating new note: note new <note description>\n"
                + "2. Viewing all notes: note list\n"
                + "3. Finding a note by keyword: note find <keyword>\n"
                + "4. Deleting a note by index number: note delete <index number>\n"
                + "5. Deleting all notes: note delete all\n\n"
                + "Other commands:\n"
                + "1. Viewing this help section: help\n"
                + "2. Closing duke conversation: bye";
    }

    /**
     * Displays appropriate error messages.
     * @param e DukeException for the error caused by incorrect user input.
     * @returns Error message.
     */
    public String showIllegalTextError(DukeException e) {
        return e.getMessage();
    }

}
