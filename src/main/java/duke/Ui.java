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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ui {

    public void displayHelloMessage() {
        display("Hello! I'm Duke\nWhat can I do for you?");
        displayLine();
    }

    public void displayLine() {
        display("--------------------------------------------------");
    }

    public void displayByeMessage() {
        displayLine();
        display("Bye. Hope to see you soon.");
        displayLine();
    }

    public void displayMessage(String command) {
        displayLine();
        display("added:" + command);
        displayLine();
    }

    public void display(Object object) {
        System.out.println(object);
    }

    public void displayList() {
        displayLine();
        display("Here are the tasks in your list:");
        for (int i = 1; i <= TaskList.dukeList.size(); i++) {
            display((i) + ". " + TaskList.dukeList.get(i - 1));
        }
        displayLine();
    }

    public void displayIncompleteTask(Task complete) {
        displayLine();
        display("OK, I've marked this task as not done yet:");
        display(complete);
        displayLine();
    }

    public void displayTaskCompletion(Task incomplete) {
        displayLine();
        display("Nice! I've marked this task as done:");
        display(incomplete);
        displayLine();
    }



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
                    display("Got it. I've added this task:");
                    String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                    String eventDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                    LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ", 3))[1]);
                    // System.out.println(descriptionAndTime[1].split(" ", 3)[1]);
                    String eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    Event newEvent = new Event(eventDescription, eventTime);
                    TaskList.add(newEvent);
                    display(newEvent);
                } else if (command.equals("deadline")) {
                    displayLine();
                    display("Got it. I've added this task:");
                    String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                    String deadlineDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                    LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ", 3))[1]);
                    // System.out.println(descriptionAndTime[1].split(" ", 3)[1]);
                    String deadlineTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    //System.out.println(deadlineTime);
//                LocalDateTime dateTime = (LocalDateTime.parse(((descriptionAndTime[1].split(" ",2))[1]), inputFormat));
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
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i = 0; i < temp.size(); i++) {
                        System.out.println(i+1 + "." + " " + temp.get(i));
                    }
                }
                else if (command.equals("todo")) {
                    if (description.trim().equals("")) {
                        throw new DukeToDoEmptyException();
                    }
                    displayLine();
                    display("Got it. I've added this task:");
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
