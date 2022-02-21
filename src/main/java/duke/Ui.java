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

/**
 * Class that defines the Ui of the app
 */
public class Ui {

    /**
     *  Method to display the welcome message.
     */
    public String displayHelloMessage() {
        String string = "Hello! I'm Duke\nWhat can I do for you?";
        display(string);
        return string;
    }



    /**
     * Method to display the breaker line.
     */
    public void displayLine() {
        display("--------------------------------------------------");
    }



    /**
     * Method to display the bye message.
     */
    public String displayByeMessage() {
        displayLine();
        String string = "Bye. Hope to see you soon.";
        display(string);
        displayLine();
        return string;
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
    public ArrayList<String> displayList() {

        displayLine();
        ArrayList<String> string = new ArrayList<>();
        displayTaskAdded();
        for (int i = 1; i <= TaskList.dukeList.size(); i++) {
            string.add(i + ". " + TaskList.dukeList.get(i - 1));
            display((i) + ". " + TaskList.dukeList.get(i - 1));
        }
        displayLine();
        return string;
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
    public void displayIncompleteTask(Task complete) {
        displayLine();
        addAsMarked();
        display(complete);
        displayLine();
    }

    /**
     * Method to display the marking of a task
     * @param incomplete The task to be marked complete
     */
    public void displayTaskCompletion(Task incomplete) {
        displayLine();
        display("Nice! I've marked this task as done:");
        display(incomplete);
        displayLine();
    }

    public String markAsAdded() {
        String string = "Got it. I've added this task:";
        display(string);
        return string;
    }

    public String stringFound() {
        String string = "Here are the matching tasks in your list:";
        display(string);
        return string;
    }

    /**
     * Method to execute command after parsing.
     * @param command The input by the user.
     * @param description The description of the task
     * @throws DukeException Throws exception if command is invalid
     * @throws IOException Throws invalid if file does not exist
     */
    public void executeCommand(String command, String description) throws DukeException, IOException {
        try {
            if (command.equals("list")) {
                displayList();
            } else if (command.equals("mark")) {
                int taskIndex = Integer.parseInt(description.substring(1));
                Task toBeCompleted = TaskList.getTask(taskIndex - 1);
                toBeCompleted.setIsDone(true);
                toBeCompleted.isComplete();
                displayTaskCompletion(toBeCompleted);
            } else if (command.equals("unmark")) {
                int taskIndex = Integer.parseInt(description.substring(1));
                Task toBeCompleted = TaskList.getTask(taskIndex - 1);
                toBeCompleted.setIsDone(false);
                //toBeCompleted.isComplete();
                displayIncompleteTask(toBeCompleted);
            } else {
                if (command.equals("event")) {
                    displayLine();
                    markAsAdded();
                    String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                    String eventDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                    LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ", 3))[1]);

                    String eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Event newEvent = new Event(eventDescription, eventTime);
                    TaskList.add(newEvent);
                    display(newEvent);
                } else if (command.equals("deadline")) {
                    displayLine();
                    markAsAdded();
                    String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                    String deadlineDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                    LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ", 3))[1]);

                    String deadlineTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Deadline newDeadline = new Deadline(deadlineDescription, deadlineTime);
                    TaskList.dukeList.add(newDeadline);
                    display(newDeadline);
                } else if (command.equals("find")){
                    ArrayList<Task> temp = new ArrayList<Task>();

                    for (Task task : TaskList.dukeList) {
                        if (task.getDescription().contains(description.substring(1))) {
                            temp.add(task);
                        }
                    }
                    stringFound();
                    for (int i = 0; i < temp.size(); i++) {
                        System.out.println(i+1 + "." + " " + temp.get(i));
                    }
                }
                else if (command.equals("todo")) {
                    if (description.trim().equals("")) {
                        throw new DukeToDoEmptyException();
                    }
                    displayLine();
                    markAsAdded();
                    ToDo newTodo = new ToDo(description.substring(1));
                    TaskList.dukeList.add(newTodo);
                    display(newTodo);
                } else if (command.equals("delete")) {
                    int taskIndex = Integer.parseInt(description.substring(1));
                    displayLine();
                    display("Noted. I've removed this task: ");
                    Task taskToBeDeleted = TaskList.getTask(taskIndex - 1);
                    display("  " + taskToBeDeleted);
                    TaskList.delete(taskIndex - 1);
                } else if (command.equals("snooze")) {
                    //display(description);
                    String[] intAndDate = description.split(" ");
                    //display(intAndDate[2]);
                    LocalDate date = LocalDate.parse(intAndDate[2]);
                    String deadlineTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    int taskIndex = Integer.parseInt(intAndDate[1]);
                    //display(taskIndex);
                    Task task = TaskList.dukeList.get(taskIndex - 1);
                    //display(task);
                    displayLine();
                    if (task instanceof ToDo){

                        display("This is a ToDo task, it cannot be snoozed :(");
                    } else if (task instanceof Deadline) {
                        String taskDescription = task.getDescription();
                        Deadline deadline = new Deadline(taskDescription, deadlineTime);
                        TaskList.dukeList.set(taskIndex - 1, deadline);
                        display("Okay, I have snoozed the task for you");
                    } else {
                        String taskDescription = task.getDescription();
                        Event event = new Event(taskDescription, deadlineTime);
                        TaskList.dukeList.set(taskIndex - 1, event);
                        display("Okay, I have snoozed the task for you");
                    }
                    displayLine();

                } else {
                    throw new DukeUnknownCommandException();
                }
                display("Now you have " + TaskList.dukeList.size() + " tasks in the list.");
                displayLine();
            }
        } catch (DukeToDoEmptyException | DukeUnknownCommandException e) {
            displayLine();
            display(e.getMessage());
            displayLine();
        }
    }
}
