package duke;

import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * The Duke program implements a simple task bot with CRUD functionality.
 * The program can add three different types of tasks (todo, deadline,
 * event), mark tasks as done, and delete tasks.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke specifying UI and Storage.
     *
     * It loads Tasks from a file into tasks.
     * If there is an error with loading Tasks from the specified file, it
     * initializes tasks to bean empty TaskList.
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(Ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    /**
     * Marks Task as complete.
     *
     * @param input index of task
     * @return Duke's response and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath or NumberFormatException
     */
    public Response doHandler(String input) throws Exception {

        int i = Integer.parseInt(input.replaceAll("[^0-9]",
                "")) - 1;
        assert i <= tasks.size() && i >= 0 : "index of item should be within scope of tasklist";
        tasks.get(i).markComplete();
        assert tasks.get(i).getStatusIcon().equals("\u2713") : "task should be marked done";

        storage.save(tasks);
        return new Response(Ui.showDoMessage(), Expression.HAPPY);
    }

    /**
     * Marks Task as incomplete.
     *
     * @param input index of task
     * @return Duke's response and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath or NumberFormatException
     */
    public Response undoHandler(String input) throws Exception {
        int i = Integer.parseInt(input.replaceAll("[^0-9]",
                "")) - 1;
        assert i <= tasks.size() && i >= 0 : "index of item should be within scope of tasklist";
        tasks.get(i).markIncomplete();
        assert tasks.get(i).getStatusIcon().equals(" ") : "task should be marked incomplete";

        storage.save(tasks);
        return new Response(Ui.showUndoMessage(), Expression.DISAPPOINTED);
    }

    /**
     * Deletes Task at a specified index from tasks.
     *
     * @param input index of task
     * @return Response of list of tasks, deletion message, and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath or NumberFormatException
     */
    public Response deleteHandler(String input) throws Exception {
        int i = Integer.parseInt(input.replaceAll("[^0-9]",
                "")) - 1;
        assert i <= tasks.size() && i >= 0 : "index of item should be within scope of tasklist";
        tasks.remove(i);

        storage.save(tasks);
        return new Response(Ui.showDeleteMessage(tasks), Expression.THUMBSUP);
    }

    /**
     * Returns a string of the list of Tasks.
     *
     * @return Response of formatted string of list of tasks and
     * expression type.
     */
    public Response listHandler() {
        return new Response(Ui.showListMessage(tasks), Expression.DEFAULT);
    }

    /**
     * Adds a Todo to tasks and returns string about the addition.
     *
     * @param input description of Todo
     * @return Duke's response and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath
     */
    public Response todoHandler(String input) throws Exception {
        Todo t = new Todo(input);
        tasks.add(t);

        storage.save(tasks);
        return new Response(Ui.showTodoMessage() + t, Expression.THUMBSUP);
    }


    /**
     * Finds tasks with matching keywords in tasklist and returns them.
     *
     * @param input keyword to search for
     * @return Response of matching tasks and expression type.
     */
    public Response findHandler(String input) {
        return new Response(Ui.showFindMessage(tasks.find(input)),
                Expression.DEFAULT);
    }

    /**
     * Adds a Deadline task to tasks and returns string about the addition.
     *
     * @param input string containing description and dateTime of task
     * @return Duke's response and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath
     */
    public Response deadlineHandler(String input) throws Exception {
        String datetime = input.replaceAll(".* by ", "");
        input = input.replaceAll(" by .*", "");

        Deadline d = new Deadline(input, datetime);
        tasks.add(d);

        storage.save(tasks);
        return new Response(Ui.showDeadlineMessage() + d, Expression.THUMBSUP);
    }

    /**
     * Adds an Event task to tasks and returns string about the addition.
     *
     * @param input string containing description and dateTime of task
     * @return Duke's response and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath
     */
    public Response eventHandler(String input) throws Exception {
        String time = input.replaceAll(".* at ", "");
        input = input.replaceAll(" at .*", "");
        Event e = new Event(input, time);
        tasks.add(e);
        storage.save(tasks);
        return new Response(Ui.showEventMessage() + e, Expression.THUMBSUP);
    }

    /**
     * Adds a doAfter task to tasks and returns string about the addition.
     *
     * @param input string containing description and dateTime of task
     * @return Duke's response and expression type.
     * @throws Exception if an exception occurs in the saving of
     * data to the file at filePath
     */
    public Response doAfterHandler(String input) throws Exception {
        String time = input.replaceAll(".* after ", "");
        input = input.replaceAll(" after .*", "");
        DoAfter e = new DoAfter(input, time);
        tasks.add(e);
        storage.save(tasks);
        return new Response(Ui.showDoAfterMessage() + e, Expression.THUMBSUP);
    }

    /**
     * Returns 'EXIT' string to signal program termination.
     *
     * @return Duke's response and expression type.
     */
    public Response byeHandler() {
        Response r = new Response(Ui.showByeMessage(), Expression.DEFAULT);
        r.setExit();
        return r;
    }

    /**
     * Returns response to indecipherable input.
     *
     * @param input string to respond to
     * @return generic response indicative of incorrect input and
     * expression type.
     */
    public Response defaultHandler(String input) {
        String response = input.equals("")
                ? Ui.showEmptyMessage()
                : Ui.showDefaultMessage();
        return new Response(response, Expression.DEFAULT);
    }


    /**
     * Determines Duke's responses to user input.
     *
     * @param input user input
     * @return Duke's response and expression type.
     */
    public Response getResponse(String input) {
        try {
            String command = Parser.parse(input, tasks);
            input = Parser.handleInput(input);

            switch (command) {
            case "list":
                return listHandler();
            case "do":
                return doHandler(input);
            case "undo":
                return undoHandler(input);
            case "delete":
                return deleteHandler(input);
            case "todo":
                return todoHandler(input);
            case "find":
                return findHandler(input);
            case "deadline":
                return deadlineHandler(input);
            case "event":
                return eventHandler(input);
            case "doafter":
                return doAfterHandler(input);
            case "bye":
                return byeHandler();
            default:
                return defaultHandler(input);
            }
        } catch (NumberFormatException e) {
            return new Response(Ui.showNumberFormatMessage(), Expression.DISAPPOINTED);
        } catch (Exception e) {
            return new Response(Ui.showError(e.getMessage()), Expression.DISAPPOINTED);
        }
    }
}
