package istjbot.ui;

/**
 * Encapsulates text part of the User Interface, which is rendered via GUI
 * through the use of JavaFX.
 */
public class Ui {

    /**
     * Returns a String of a welcome message.
     *
     * @return String of a welcome message.
     */
    public static String showWelcome() {
        String welcomeMessage = "Hello! I'm IstjBot. \n" + "What can I do for you?";
        return welcomeMessage;
    }

    /**
     * Returns a String of a confirmation message that the task has been successfully added.
     *
     * @param tasksSize Integer of number of tasks in the list.
     * @param addedTask String representation of added task.
     * @return String of confirmation message.
     */
    public String showTaskAdded(int tasksSize, String addedTask) {
        assert tasksSize > 0 : "there should now be at least one task";

        String initialMessage = "As an IstjBot, I will add this task right now. \n";
        // May refactor finalMessage?
        String finalMessage = "Now you have " + tasksSize + " ";
        String plural = tasksSize > 1 ? "tasks" : "task";
        finalMessage += plural + " in the list.";
        return initialMessage + addedTask + "\n" + finalMessage;
    }

    /**
     * Returns a String of a confirmation message that the note has been successfully added.
     *
     * @param notesSize Integer of number of notes in the list.
     * @param addedNote String representation of added note.
     * @return String of confirmation message.
     */
    public String showNoteAdded(int notesSize, String addedNote) {
        String initialMessage = "As an IstjBot, I will add this note right now. \n";
        String finalMessage = "Now you have " + notesSize + " ";
        String plural = notesSize > 1 ? "notes" : "note";
        finalMessage += plural + " in the list.";
        return initialMessage + addedNote + "\n" + finalMessage;
    }

    /**
     * Returns a String of a confirmation message that the task has been successfully marked as done.
     *
     * @param task String of the task marked.
     * @return String of confirmation message.
     */
    public String showTaskMarked(String task) {
        String message = "As an IstjBot, I've marked this task as done: \n" + task;
        return message;
    }

    /**
     * Returns a confirmation message that the task has been successfully unmarked as not done.
     * @param task String of the task unmarked.
     * @return String of confirmation message.
     */
    public String showTaskUnmarked(String task) {
        String message = "As an IstjBot, I've unmarked this task: \n" + task;
        return message;
    }

    /**
     * Returns a String of a confirmation message that the task has been successfully deleted.
     *
     * @param tasksSize Integer of number of tasks in the list.
     * @param deletedTask String of the task that has just been deleted.
     * @return String of confirmation message.
     */
    public String showTaskDeleted(int tasksSize, String deletedTask) {
        String initialMessage = "As an IstjBot, I've removed this task: \n";
        String finalMessage = "Now you have " + tasksSize + " ";
        String plural = tasksSize > 1 ? "tasks" : "task";
        finalMessage += plural + " in the list.";
        return initialMessage + deletedTask + "\n" + finalMessage;
    }

    /**
     * Returns a String of a confirmation message that the note has been successfully deleted.
     *
     * @param notesSize Integer of number of notes in the list.
     * @param deletedNote String of the note that has just been deleted.
     * @return String of confirmation message.
     */
    public String showNoteDeleted(int notesSize, String deletedNote) {
        String initialMessage = "As an IstjBot, I've removed this note: \n";
        String finalMessage = "Now you have " + notesSize + " ";
        String plural = notesSize > 1 ? "notes" : "note";
        finalMessage += plural + " in the list.";
        return initialMessage + deletedNote + "\n" + finalMessage;
    }

    /**
     * Returns a String consisting of tasks that have been filtered with user's input date,
     * plus IstjBot's message.
     *
     * @param searchList String of all tasks that are filtered with user's input date.
     * @return String of tasks and IstjBot's message.
     */
    public String showTasksByDate(String searchList) {
        String initialMessage = "As an IstjBot, I present you the task(s) with that date.";
        initialMessage += searchList.isEmpty() ? "" : "\n";
        return initialMessage + searchList;
    }

    /**
     * Returns a String of messages and tasks that have been filtered with user's input keyword.
     *
     * @param searchList String of all tasks that are filtered with user's input keyword.
     * @return String of filtered tasks with IstjBot's message.
     */
    public String showTasksByKeyword(String searchList) {
        String initialMessage = "As an IstjBot, I present you the task(s) with that keyword.";
        initialMessage += searchList.isEmpty() ? "" : "\n";
        return initialMessage + searchList;
    }

    /**
     * Returns a String consisting of all tasks that are stored plus IstjBot's message.
     *
     * @param list String of all tasks.
     * @return String of all tasks with IstjBot's message.
     */
    public String showTasks(String list) {
        String initialMessage = "As an IstjBot, I present you the task(s) in your list:";
        initialMessage += list.isEmpty() ? "" : "\n";
        return initialMessage + list;
    }

    /**
     * Returns a String consisting of all notes that are stored plus IstjBot's message.
     *
     * @param list String of all notes.
     * @return String of all notes with IstjBot's message.
     */
    public String showNotes(String list) {
        String initialMessage = "As an IstjBot, I present you the note(s) in your list:";
        initialMessage += list.isEmpty() ? "" : "\n";
        return initialMessage + list;
    }

    /**
     * Returns a String of bye message to the user.
     *
     * @return String of bye message.
     */
    public String showBye() {
        String message = "Bye, I, IstjBot, will be organizing your tasks until you come back.";
        return message;
    }

    /**
     * Returns a String of the error message.
     *
     * @param errorMessage String of the error message.
     * @return String of error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }
}
