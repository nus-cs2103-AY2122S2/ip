package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Parser {
    public TaskList taskList;
    private Ui ui;

    Parser(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public void execute(String input) {
        String[] checkCase = input.split(" ");
        switch (checkCase[0].toLowerCase()) {

            case ("list"): 
                ui.showListMessage(taskList);
                break;

            case ("mark"):
                try {
                    int index = Integer.parseInt(checkCase[1]) - 1;
                    Task tasks = taskList.getTaskArray().get(index);
                    System.out.println(tasks.marking(checkCase[0].toLowerCase()));
                    taskList.getTaskArray().set(index, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showInvalidInput();
                }
                break;

            case ("unmark"):
                try {
                    int index = Integer.parseInt(checkCase[1]) - 1;
                    Task tasks = taskList.getTaskArray().get(index);
                    System.out.println(tasks.marking(checkCase[0].toLowerCase()));
                    taskList.getTaskArray().set(index, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showInvalidInput();
                }
                break;

            case ("todo"):

                try {
                    String toDoCondition = "todo ";
                    int indexOfToDo = toDoCondition.length(); //to find todo
                    String stringSliced = input.substring(indexOfToDo,input.length());
                    Todo todoTask = new Todo(stringSliced);
                    taskList.addTask(todoTask);
                    String noOfTask = String.valueOf(taskList.getTaskArray().size());
                    ui.showAddedMessage(todoTask, noOfTask);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showTodoError();
                }
                break;

            case ("deadline"):

                try {
                    String deadlineCondition = "/by ";
                    int indexOfTime = input.indexOf(deadlineCondition); //to find /
                    String dateTime = input.substring(indexOfTime + deadlineCondition.length(), input.length()); // the date and time for by
                    //convert to the correct one
                    LocalDateTime deadlineTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
                    String convertedTime = deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                    String stringSliced = input.substring(9, indexOfTime); // after deadline
                    Deadline deadlineTask = new Deadline(stringSliced, convertedTime);
                    taskList.addTask(deadlineTask);
                    String noOfTask = String.valueOf(taskList.getTaskArray().size());
                    ui.showAddedMessage(deadlineTask, noOfTask);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showDeadlineError();
                }
                break;

            case ("event"):

                try {
                    String eventCondition = "/at ";
                    int indexOfTime = input.indexOf(eventCondition); //to find /
                    String dateTime = input.substring(indexOfTime + eventCondition.length(), input.length()); // the date and time for at
                    //convert to the correct one
                    LocalDateTime eventTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
                    String convertedTime = eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                    String stringSliced = input.substring(6, indexOfTime); // after deadline
                    Event eventTask = new Event(stringSliced, convertedTime);
                    taskList.addTask(eventTask);
                    String noOfTask = String.valueOf(taskList.getTaskArray().size());
                    ui.showAddedMessage(eventTask, noOfTask);

                } catch (StringIndexOutOfBoundsException e) {
                    ui.showEventError();
                }
                break;

            case("delete"):

                try {
                    int index = Integer.parseInt(checkCase[1]) - 1;
                    Task task = taskList.getTaskArray().get(index);
                    taskList.deleteTask(index);
                    String noOfTask = String.valueOf(taskList.getTaskArray().size());
                    ui.showDeletedMessage(task, noOfTask);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showDeleteError();
                }
                break;

            case("bye"):
                ui.showGoodbyeMessage();
                break;             

            default:
                ui.showDefaultMessage();
                break;



        }


    }





}
