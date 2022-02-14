package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a class which is responsile to make sense
 * of the user input.
 */
class Parser {
    private TaskList taskList;
    private Ui ui;

    Parser(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Executes and makes sense of the user command.
     *
     * @param input Input given by the user .
     */
    public String execute(String input) {
        String[] checkCase = input.split(" ");
        switch (checkCase[0].toLowerCase()) {
        case ("list"):
            return ui.showListMessage(taskList);

        case ("mark"):
            try {
                int index = Integer.parseInt(checkCase[1]) - 1;
                Task tasks = taskList.getTaskArray().get(index);
                String output = tasks.marking(checkCase[0].toLowerCase());
                taskList.getTaskArray().set(index, tasks);
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showInvalidInput();
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidInput();
            }

        case ("unmark"):
            try {
                int index = Integer.parseInt(checkCase[1]) - 1;
                Task tasks = taskList.getTaskArray().get(index);
                String output = tasks.marking(checkCase[0].toLowerCase());
                taskList.getTaskArray().set(index, tasks);
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showInvalidInput();
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidInput();
            }

        case ("todo"):

            try {
                String toDoCondition = "todo ";
                int indexOfToDo = toDoCondition.length(); //to find todo
                String stringSliced = input.substring(indexOfToDo);
                Todo todoTask = new Todo(stringSliced);
                taskList.addTask(todoTask);
                String noOfTask = String.valueOf(taskList.getTaskArray().size());
                return ui.showAddedMessage(todoTask, noOfTask);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showTodoError();
            }

        case ("deadline"):

            try {
                String deadlineCondition = "/by ";
                int indexOfTime = input.indexOf(deadlineCondition); //to find /
                String dateTime = input.substring(indexOfTime + deadlineCondition.length());
                //convert to the correct one
                LocalDateTime deadlineTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
                String convertedTime = deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                String stringSliced = input.substring(9, indexOfTime); // after deadline
                Deadline deadlineTask = new Deadline(stringSliced, convertedTime);
                taskList.addTask(deadlineTask);
                String noOfTask = String.valueOf(taskList.getTaskArray().size());
                return ui.showAddedMessage(deadlineTask, noOfTask);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showDeadlineError();
            }

        case ("event"):

            try {
                String eventCondition = "/at ";
                int indexOfTime = input.indexOf(eventCondition); //to find /
                String dateTime = input.substring(indexOfTime + eventCondition.length());
                //convert to the correct one
                LocalDateTime eventTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
                String convertedTime = eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                String stringSliced = input.substring(6, indexOfTime); // after deadline
                Event eventTask = new Event(stringSliced, convertedTime);
                taskList.addTask(eventTask);
                String noOfTask = String.valueOf(taskList.getTaskArray().size());
                return ui.showAddedMessage(eventTask, noOfTask);

            } catch (StringIndexOutOfBoundsException e) {
                return ui.showEventError();
            }

        case("delete"):

            try {
                int index = Integer.parseInt(checkCase[1]) - 1;
                Task task = taskList.getTaskArray().get(index);
                taskList.deleteTask(index);
                String noOfTask = String.valueOf(taskList.getTaskArray().size());
                return ui.showDeletedMessage(task, noOfTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showDeleteError();
            }

        case("find"):
            String findCondition = "find ";
            String stringSliced = input.substring(findCondition.length());
            return ui.showFindMessage(taskList.findTask(stringSliced));

        case("bye"):
            return ui.showGoodbyeMessage();

        default:
            return ui.showDefaultMessage();
        }
    }





}
