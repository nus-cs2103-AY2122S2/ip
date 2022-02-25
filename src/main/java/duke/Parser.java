package duke;

import java.time.LocalDate;
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
     * @param input Input given by the user.
     * @return String output which will be shown in the Duke chat.
     */
    public String execute(String input) {
        String[] checkCase = input.split(" ");
        switch (checkCase[0].toLowerCase()) {
        case ("list"):
            return taskList.showTask();

        case ("mark"):
            try {
                int index = Integer.parseInt(checkCase[1]) - 1;
                Task tasks = taskList.getTaskArray().get(index);
                String output = tasks.mark();
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
                String output = tasks.unmark();
                taskList.getTaskArray().set(index, tasks);
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.showInvalidInput();
            } catch (IndexOutOfBoundsException e) {
                return ui.showInvalidInput();
            }

        case ("todo"):

            try {
                String stringSliced = parseString(input);
                Todo todoTask = new Todo(stringSliced);
                taskList.addTask(todoTask);
                String noOfTask = String.valueOf(taskList.getTaskArray().size());
                return ui.showAddedMessage(todoTask, noOfTask);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showTodoError();
            }

        case ("deadline"):

            try {
                String parsedTime = parseByCondition(input);
                String convertedTime = convertTime(parsedTime);
                String stringSliced = parseString(input);
                Deadline deadlineTask = new Deadline(stringSliced, convertedTime);
                taskList.addTask(deadlineTask);
                String noOfTask = String.valueOf(taskList.getTaskArray().size());
                return ui.showAddedMessage(deadlineTask, noOfTask);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showDeadlineError();
            }

        case ("event"):

            try {
                String parsedTime = parseByCondition(input);
                String convertedTime = convertTime(parsedTime);
                String stringSliced = parseString(input);
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
            String stringSliced = parseString(input);
            return ui.showFindMessage(taskList.findTask(stringSliced));

        case("view"):
            String parsedDate = parseString(input);
            String convertedDate = convertDate(parsedDate);
            return ui.showScheduleMessage(taskList, convertedDate);

        case("bye"):
            return ui.showGoodbyeMessage();

        default:
            return ui.showDefaultMessage();
        }
    }

    /**
     * Parses the input by the respective conditions - 'by' for deadline and 'at' for event.
     *
     * @param input Input given by the user.
     * @return String output of the parsed input.
     */
    public String parseByCondition(String input) {
        String deadlineCondition = "/by ";
        int indexBy = input.indexOf(deadlineCondition);
        String eventCondition = "/at ";
        int indexAt = input.indexOf(eventCondition);
        String output;
        if (input.contains(deadlineCondition)) {
            output = input.substring(indexBy + deadlineCondition.length());
        } else {
            output = input.substring(indexAt + eventCondition.length());
        }
        return output;
    }
    /**
     * Converts the time input into correct format.
     *
     * @param dateTime Input given by the user.
     * @return String result of the converted date and time in the right format.
     */
    public String convertTime(String dateTime) {
        LocalDateTime convertedTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
        String result = convertedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return result;
    }

    /**
     * Parses the input by the respective tasks.
     *
     * @param input Input given by the user.
     * @return String output of the parsed input.
     */
    public String parseString(String input) {
        String deadline = "deadline ";
        String event = "event ";
        String todo = "todo ";
        String find = "find ";
        String view = "view ";
        String deadlineCondition = "/by";
        String eventCondition = "/at ";
        String output;
        if (input.contains(todo)) {
            output = input.substring(todo.length());
        } else if (input.contains(event)) {
            output = input.substring(event.length(), input.indexOf(eventCondition));
        } else if (input.contains(deadline)) {
            output = input.substring(deadline.length(), input.indexOf(deadlineCondition));
        } else if (input.contains(find)) {
            output = input.substring(find.length());
        } else {
            output = input.substring(view.length());
        }
        return output;
    }
    public String convertDate(String date) {
        LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/y"));
        String result = convertedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return result;
    }




}
