package duke;

import exceptions.DukeException;
import exceptions.DukeToDoEmptyException;
import exceptions.DukeUnknownCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that defines the Ui of the app
 */
public class Ui {

    private Storage storage = new Storage("./data/duke.txt");

    /**
     *  Method to display the welcome message.
     */
    public StringBuilder displayHelloMessage(StringBuilder str) {
        str.append("Hello! I'm duke.Duke\nWhat can I do for you?");
        str.append("\n");
        return str;
    }



    /**
     * Method to display the breaker line.
     */
    public String displayLine() {
        return("--------------------------------------------------");
    }



    /**
     * Method to display the bye message.
     */
    public StringBuilder displayByeMessage(StringBuilder str) {
        str.append(displayLine());
        str.append("\n");
        str.append("Bye. Hope to see you soon.");
        str.append("\n");
        String string = "Bye. Hope to see you soon.";
        str.append(displayLine());
        str.append("\n");
        return str;
    }

    public String displayMessage(String command) {
        String string = "added:" + command;
        displayLine();
        display(string);
        displayLine();
        return string;
    }


    /**
     * Method to display any object.
     * @param object Object to be displayed.
     */
    public void display(Object object) {
        System.out.println(object);
    }


    public String displayTaskAdded() {
        String string = "Here are the tasks in your list:";
        display(string);
        return string;
    }

    /**
     * Method to display the list to the user
     */
    public StringBuilder displayList(StringBuilder str) {

        str.append(displayLine());
        str.append("\n");

        str.append("Here are the tasks in your list:");
        str.append("\n");
        for (int i = 1; i <= TaskList.dukeList.size(); i++) {
            String string = i + ". " + TaskList.dukeList.get(i - 1);
            str.append(string);
            str.append("\n");
        }
        str.append(displayLine());
        return str;
    }

    public String addAsMarked() {
        String string = "OK, I've marked this task as not done yet:";
        display(string);
        return string;
    }

    /**
     *  Method to display the unmarking of a task.
     * @param complete The task that is to be unmarked.
     */
    public void displayIncompleteTask(Task complete, StringBuilder str) {
        str.append("OK, I've marked this task as not done yet:");
        str.append("\n");
        str.append(complete);
        str.append("\n");
        str.append(displayLine());
    }

    /**
     * Method to display the marking of a task
     * @param incomplete The task to be marked complete
     */
    public StringBuilder displayTaskCompletion(Task incomplete, StringBuilder str) {
        str.append("Nice! I've marked this task as done:");
        str.append("\n");
        str.append(incomplete);
        str.append("\n");
        str.append(displayLine());
        return str;
    }

    public StringBuilder markAsAdded(StringBuilder str) {
        str.append(displayLine());
        str.append("Got it. I've added this task:\n");
        return str;
    }

    public StringBuilder stringFound (StringBuilder str) {
        str.append("\n");
        str.append(displayLine());
        str.append("Here are the matching tasks in your list:");
       return str;
    }

    /**
     * Method to execute command after parsing.
     * @param command The input by the user.
     * @param description The description of the task
     * @throws DukeException Throws exception if command is invalid
     * @throws IOException Throws invalid if file does not exist
     */
    public String executeCommand(String command, String description, StringBuilder str) throws DukeException, IOException {
        try {
            if (command.equals("list")) {
                displayList(str);
            } else if (command.equals("mark")) {
                int taskIndex = Integer.parseInt(description);
                Task toBeCompleted = TaskList.getTask(taskIndex - 1);
                toBeCompleted.setIsDone(true);
                toBeCompleted.isComplete();
                str.append(displayLine());
                str.append("\n");
                displayTaskCompletion(toBeCompleted, str);
            } else if (command.equals("unmark")) {
                int taskIndex = Integer.parseInt(description);
                Task toBeCompleted = TaskList.getTask(taskIndex - 1);
                toBeCompleted.setIsDone(false);
                //toBeCompleted.isComplete();
                str.append(displayLine());
                str.append("\n");
                displayIncompleteTask(toBeCompleted, str);
            } else {
                if (command.equals("event")) {
                    markAsAdded(str);

                    String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                    String eventDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                    LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ", 3))[1]);

                    String eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Event newEvent = new Event(descriptionAndTime[0], eventTime);
                    TaskList.dukeList.add(newEvent);
                    str.append(newEvent);
                } else if (command.equals("deadline")) {
                    markAsAdded(str);
                    String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                    String deadlineDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                    LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ", 3))[1]);

                    String deadlineTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Deadline newDeadline = new Deadline(descriptionAndTime[0], deadlineTime);
                    TaskList.dukeList.add(newDeadline);
                    str.append(newDeadline);
                } else if (command.equals("find")){
                    ArrayList<Task> temp = new ArrayList<Task>();

                    for (Task task : TaskList.dukeList) {
                        if (task.getDescription().contains(description)) {
                            temp.add(task);
                        }
                    }
                    stringFound(str);
                    for (int i = 0; i < temp.size(); i++) {
                        String string = i+1 + "." + " " + temp.get(i) + "\n";
                        str.append(string);
                    }
                }
                else if (command.equals("todo")) {
                    if (description.trim().equals("")) {
                        throw new DukeToDoEmptyException();
                    }
                    markAsAdded(str);
                    ToDo newTodo = new ToDo(description);
                    TaskList.dukeList.add(newTodo);
                    str.append(newTodo);
                } else if (command.equals("delete")) {
                    int taskIndex = Integer.parseInt(description);
                    str.append(displayLine());
                    str.append("\n");
                    str.append("Noted. I've removed this task: ");
                    Task taskToBeDeleted = TaskList.getTask(taskIndex - 1);
                    String string = "  " + taskToBeDeleted;
                    str.append(string);
                    TaskList.delete(taskIndex - 1);
                } else if (command.equals("snooze")) {
                    //display(description);
                    String[] intAndDate = description.split(" ");
                    //display(intAndDate[2]);
                    LocalDate date = LocalDate.parse(intAndDate[1]);
                    String deadlineTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    int taskIndex = Integer.parseInt(intAndDate[0]);
                    //display(taskIndex);
                    Task task = TaskList.dukeList.get(taskIndex - 1);
                    //display(task);
                    str.append(displayLine());
                    str.append("\n");
                    if (task instanceof ToDo){
                        str.append("This is a ToDo task, it cannot be snoozed :(");
                        str.append("\n");
                    } else if (task instanceof Deadline) {
                        String taskDescription = task.getDescription();
                        Deadline deadline = new Deadline(taskDescription, deadlineTime);
                        TaskList.dukeList.set(taskIndex - 1, deadline);
                        str.append("Okay, I have snoozed the task for you");
                        str.append("\n");
                    } else {
                        String taskDescription = task.getDescription();
                        Event event = new Event(taskDescription, deadlineTime);
                        TaskList.dukeList.set(taskIndex - 1, event);
                        str.append("Okay, I have snoozed the task for you");
                        str.append("\n");
                    }
                    str.append(displayLine());
                    str.append("\n");

                } else if (command.equals("bye")) {
                    displayByeMessage(str);
                    storage.saveData();
                }
                else {
                    throw new DukeUnknownCommandException();
                }
                String string = "\n"+ "Now you have " + TaskList.dukeList.size() + " tasks in the list.";
                str.append(string);
                str.append("\n");
                str.append(displayLine());
                str.append("\n");

            }
        } catch (DukeToDoEmptyException | DukeUnknownCommandException e) {
            str.append(displayLine());
            str.append("\n");
            str.append(e.getMessage());
            str.append("\n");
            str.append(displayLine());
            str.append("\n");
        } return str.toString();
    }
}
