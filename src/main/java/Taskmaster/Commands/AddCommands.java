package Taskmaster.Commands;

import Taskmaster.Exception.DukeExceptions;
import Taskmaster.util.TaskList;
import Taskmaster.Task.Task;
import Taskmaster.Task.TodoTask;
import Taskmaster.Task.DeadlineTask;
import Taskmaster.Task.EventTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommands extends Commands {
    private TaskList tasklist;

    public AddCommands(String command, TaskList tasklist) {
        super(command);
        this.tasklist = tasklist;
    }

    private void parseCommand() {
        String splitString[] = this.command.split(" ");
        String firstWord = splitString[0];

        try {
            //Handle the case of having no second input
            if (splitString.length == 1) {
                throw new DukeExceptions("What?! Task description cannot be empty."
                        + "Eg todo eat, deadline eat food /by 12pm,"
                        + "event concert /at 8pm\n");
            }

            String taskName = command.substring(command.indexOf(" "));

            if (firstWord.equals("todo")) {
                addTodoTask(taskName);
            } else {
                parseDeadlineEventTasks();
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    private void parseDeadlineEventTasks() {
        String splitString[] = this.command.split(" ");
        String firstWord = splitString[0];
        String taskName = command.substring(command.indexOf(" "));
        try {
            //Handle the case of having no "/" to specify deadline or time of occurrences for deadline and event tasks
            if (!taskName.contains("/")) {
                throw new DukeExceptions("Deadline and event tasks require /by and /at to specify the deadline or time of occurrence.\n"
                        + " Eg Deadline eat food /by 12pm, event concert /at 8pm");
            }

            String temp = taskName.substring(taskName.indexOf("/"));
            String taskNameWithoutBack = taskName.substring(0, taskName.indexOf("/"));
            String oldDateTime = temp.substring(temp.indexOf(" ") + 1);

            DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(oldDateTime, oldFormat);


            //String that states by ... or at....
            if (firstWord.equals("deadline")) {
                //Handle the case of deadline task having no /by
                if (!temp.contains("by")) {
                    throw new DukeExceptions("Deadline tasks require /by specify the deadline."
                                            + "Eg deadline eat food /by 12pm\n");
                }
                    addDeadlineTask(taskNameWithoutBack, dateTime);
                } else {
                    //Handle the case of event task having no /at
                    if (!temp.contains("at")) {
                        throw new DukeExceptions("Event tasks require /at specify the time of occurrence."
                                                    + "music concert /at 8pm\n");
                    }
                    addEventTask(taskNameWithoutBack, dateTime);
                }

        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ERROR! Expected Numbers for date and time!\n");
        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Time or Date is in wrong format! 2/12/2019 1800\n");
        }
    }

    private void addTodoTask(String taskName) {
        TodoTask newTask = new TodoTask(taskName);
        tasklist.add(newTask);
        printTask(newTask);
    }

    private void addDeadlineTask(String taskName, LocalDateTime dateTime){
        DeadlineTask newTask = new DeadlineTask(taskName, dateTime);
        tasklist.add(newTask);
        printTask(newTask);
    }

    private void addEventTask(String taskName, LocalDateTime dateTime){
        EventTask newTask = new EventTask(taskName, dateTime);
        tasklist.add(newTask);
        printTask(newTask);
    }


    private void printTask(Task newTask) {
        System.out.println("Quit ordering me around!");
        System.out.println("I've added this task to our list:");
        System.out.println("    " + newTask.toString());
        System.out.println("Now you have " + tasklist.currentSize + " tasks in the list.\n");
    }


    public void execute() {
        parseCommand();
    }







}
