package duke.parser;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser for reading input from the user.
 */
public class Parser {

    /**
     * Returns boolean stating whether or not the user wants to quit Duke.
     * Handles logic for each implemented command.
     *
     * @param input User's input.
     * @param taskList Current list of tasks.
     * @return Whether user is quitting Duke.
     * @throws DukeException If user enters unknown command.
     */
    public static boolean parse(String input, TaskList taskList) throws DukeException {
        if (input.equals("bye")) {
            Ui.print("BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!");
            return true;
        } else if (input.equals("list")) {
            handleList(taskList);
        } else {
            //Split command into 2 parts, the type of task, and remaining text
            String[] inputArgs = input.split(" ", 2);
            String command = inputArgs[0];

            //Assuming input correct
            if (command.equals("mark")) {
                handleMark(taskList, inputArgs, true);
            } else if (command.equals("unmark")) {
                handleMark(taskList, inputArgs, false);
            } else if (command.equals("delete")) {
                handleDelete(taskList, inputArgs);
            } else if (command.equals("deadline")) {
                handleDeadline(taskList, inputArgs);
            } else if (command.equals("event")) {
                handleEvent(taskList, inputArgs);
            } else if (command.equals("todo")) {
                handleToDo(taskList, inputArgs);
            } else {
                throw new DukeException("WHAT TALKING YOU? CHAO RECRUIT YOU BETTER WAKE UP YOUR IDEA!");
            }
        }
        return false;
    }

    /**
     * Handles logic for list command.
     * Prints all tasks in taskList
     *
     * @param taskList Current list of tasks.
     */
    private static void handleList(TaskList taskList) {
        //Add name of task to str for easy printing
        int size = taskList.size();
        if (size == 0) {
            Ui.print("NOTHING TO DO AH?");
            Ui.print("YOU BETTER FIND SOMETHING TO DO BEFORE I CONFINE YOU CHAO RECRUIT!");
        } else {
            Ui.print("NEED ME TO REMIND YOU AH?");
            for (int i = 0; i < size; i++) {
                Ui.print((i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * Checks if input is incomplete.
     * If input is incomplete, returns true.
     *
     * @param inputArgs String array made up of individual words from input.
     * @returns Whether input is incomplete.
     */
    private static boolean checkIncompleteness(String[] inputArgs) {
        return (inputArgs.length < 2 || inputArgs[1].isBlank());
    }

    /**
     * Handles logic for mark/ unmark command.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @param isMark Whether to mark or unmark the task.
     * @throws DukeException If number of task to mark/unmark not specified.
     */
    private static void handleMark(TaskList taskList, String[] inputArgs, boolean isMark) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU WANT MARK? WEAR HELMET CANNOT THINK ALREADY AH?");
        } else {
            int num = Integer.parseInt(inputArgs[1]) - 1;
            if (isMark) {
                taskList.get(num).mark();
                Ui.print("THIS ONE");
                Ui.print(taskList.get(num));
                Ui.print("FINISH ALREADY AH? SWEE CHAI BUTTERFLY RECRUIT!");
            } else {
                taskList.get(num).unmark();
                Ui.print("I THOUGHT THIS ONE");
                Ui.print(taskList.get(num));
                Ui.print("FINISH ALREADY? NEVER MIND THIS WEEKEND CONFINE!");
            }
        }
    }

    /**
     * Handles logic for delete command.
     * Deletes selected task.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @throws DukeException If number of task to mark/unmark not specified.
     */
    private static void handleDelete(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("YOU TRYING TO LEPAK IS IT? WAKE UP YOUR BLOODY IDEA!");
        } else {
            int num = Integer.parseInt(inputArgs[1]) - 1;
            Task toDelete = taskList.get(num);
            taskList.remove(num);
            int size = taskList.size();
            Ui.print("YOU DON'T WANT DO THEN SAY DON'T DO AH?");
            Ui.print(toDelete);
            Ui.print("WAKE UP YOUR BLOODY IDEA CHAO RECRUIT!");
            if (size == 0) {
                Ui.print("NOTHING ELSE TO DO CAN RILEK ALREADY AH RECRUIT? DOWN 20!");
            } else {
                Ui.print(size + " MORE TASKS REMAINING! YOU BETTER ONE TIMES GOOD ONE!");
            }
        }
    }

    /**
     * Handles logic for deadline command.
     * Creates new Deadline with supplied information.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @throws DukeException If input is incomplete.
     */
    private static void handleDeadline(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
        } else {
            String remainder = inputArgs[1];
            //DateTimeFormatter pattern for reading date and time
            DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
            String taskName = remainder.split(" /by ")[0];
            String dateTime = remainder.split(" /by ")[1];
            String date = dateTime.split(" ")[0];
            String time = dateTime.split(" ")[1];
            LocalDate d1 = LocalDate.parse(date, dateInputFormatter);
            LocalTime t1 = LocalTime.parse(time, timeInputFormatter);
            Task newTask = new Deadline(taskName, d1, t1);
            taskList.add(newTask);
            Ui.print("YOU BETTER FINISH THIS AH:");
            Ui.print(newTask);
            Ui.print("YOU STILL GOT " + taskList.size() + " THINGS TO DO AH BETTER NOT FORGET!");
        }
    }

    /**
     * Handles logic for event command.
     * Creates new Event with supplied information.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @throws DukeException If input is incomplete.
     */
    private static void handleEvent(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
        } else {
            String remainder = inputArgs[1];
            //DateTimeFormatter pattern for reading date and time
            DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeInputFormatter = DateTimeFormatter.ofPattern("HHmm");
            String taskName = remainder.split(" /at ")[0];
            String dateTime = remainder.split(" /at ")[1];
            String date = dateTime.split(" ")[0];
            String[] time = dateTime.split(" ")[1].split("-");
            LocalDate d1 = LocalDate.parse(date, dateInputFormatter);
            LocalTime timeFrom = LocalTime.parse(time[0], timeInputFormatter);
            LocalTime timeTo = LocalTime.parse(time[1], timeInputFormatter);
            Task newTask = new Event(taskName, d1, timeFrom, timeTo);
            taskList.add(newTask);
            Ui.print("YOU BETTER REMEMBER THIS AH:");
            Ui.print(newTask);
            Ui.print("YOU STILL GOT " + taskList.size() + " THINGS TO DO AH BETTER NOT FORGET!");
        }
    }

    /**
     * Handles logic for todo command.
     * Creates new ToDo with supplied information.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @throws DukeException If input is incomplete.
     */
    private static void handleToDo(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
        } else {
            Task newTask = new ToDo(inputArgs[1]);
            taskList.add(newTask);
            Ui.print("YOU WANT TO DO THIS AH:");
            Ui.print(newTask);
            Ui.print("VERY GOOD! YOU STILL GOT " + taskList.size() + " THINGS TO DO AH BETTER NOT FORGET!");
        }
    }
}
