package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


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
     * @return Output of given command.
     * @throws DukeException If user enters unknown command.
     */
    public static String parse(String input, TaskList taskList) throws DukeException {
        if (input.equals("bye")) {
            return "BYE WHAT BYE? YOU GO DROP TWENTY THEN BYE! DOWN!";
        } else if (input.equals("list")) {
            return handleList(taskList);
        } else {
            //Split command into 2 parts, the type of task, and remaining text
            String[] inputArgs = input.split(" ", 2);
            String command = inputArgs[0];

            if (command.equals("mark")) {
                return handleMark(taskList, inputArgs, true);
            } else if (command.equals("unmark")) {
                return handleMark(taskList, inputArgs, false);
            } else if (command.equals("delete")) {
                return handleDelete(taskList, inputArgs);
            } else if (command.equals("find")) {
                return handleFind(taskList, inputArgs);
            } else if (command.equals("schedule")) {
                return handleSchedule(taskList, inputArgs);
            } else if (command.equals("deadline")) {
                return handleDeadline(taskList, inputArgs);
            } else if (command.equals("event")) {
                return handleEvent(taskList, inputArgs);
            } else if (command.equals("todo")) {
                return handleToDo(taskList, inputArgs);
            } else {
                throw new DukeException("WHAT TALKING YOU? CHAO RECRUIT YOU BETTER WAKE UP YOUR IDEA!");
            }
        }
    }

    /**
     * Handles logic for list command.
     * Prints all tasks in taskList
     *
     * @param taskList Current list of tasks.
     * @return String of all tasks.
     */
    private static String handleList(TaskList taskList) {
        //Add name of task to str for easy printing
        int size = taskList.size();
        if (size == 0) {
            return "NOTHING TO DO AH?\n"
                    + "YOU BETTER FIND SOMETHING TO DO BEFORE I CONFINE YOU CHAO RECRUIT!";
        } else {
            String output = "NEED ME TO REMIND YOU AH?\n";
            output += taskList.printTasks();
            return output;
        }
    }

    /**
     * Checks if input is incomplete.
     * If input is incomplete, returns true.
     *
     * @param inputArgs String array made up of individual words from input.
     * @return Whether input is incomplete.
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
     * @return Output of command.
     * @throws DukeException If number of task to mark/unmark not specified.
     */
    private static String handleMark(TaskList taskList, String[] inputArgs, boolean isMark) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU WANT MARK? WEAR HELMET CANNOT THINK ALREADY AH?");
        } else {
            int num = Integer.parseInt(inputArgs[1]) - 1;
            String output;
            if (isMark) {
                taskList.get(num).mark();
                output = "THIS ONE\n"
                        + "  " + taskList.get(num)
                        + "\nFINISH ALREADY AH? SWEE CHAI BUTTERFLY RECRUIT!";
            } else {
                taskList.get(num).unmark();
                output = "I THOUGHT THIS ONE\n"
                        + "  " + taskList.get(num)
                        + "\nFINISH ALREADY? NEVER MIND THIS WEEKEND CONFINE!";
            }
            return output;
        }
    }

    /**
     * Handles logic for delete command.
     * Deletes selected task.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @return Output of command.
     * @throws DukeException If number of task to mark/unmark not specified.
     */
    private static String handleDelete(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("YOU TRYING TO LEPAK IS IT? WAKE UP YOUR BLOODY IDEA!");
        } else {
            int num = Integer.parseInt(inputArgs[1]) - 1;
            Task toDelete = taskList.get(num);
            taskList.remove(num);
            int size = taskList.size();
            String output = "YOU DON'T WANT DO THEN SAY DON'T DO AH?\n"
                    + "  " + toDelete
                    + "\nWAKE UP YOUR BLOODY IDEA CHAO RECRUIT!\n";
            if (size == 0) {
                output += "NOTHING ELSE TO DO CAN RILEK ALREADY AH RECRUIT? DOWN 20!";
            } else if (size > 0) {
                output += size + " MORE TASKS REMAINING! YOU BETTER ONE TIMES GOOD ONE!";
            } else {
                // When size < 0, should not occur.
                throw new DukeException("NOTHING TO DO ALREADY STILL WANT CHAO GENG AH! THIS WEEKEND YOU WATCH OUT!");
            }
            return output;
        }
    }

    /**
     * Handles logic for find command.
     * Looks for and displays tasks containing keyword.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @return Output of command.
     * @throws DukeException If keyword missing.
     */
    private static String handleFind(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU TRYING TO FIND? WAKE UP YOUR BLOODY IDEA!");
        } else {
            String keyword = inputArgs[1];
            TaskList tempTaskList = new TaskList();
            int size = taskList.size();

            for (int i = 0; i < size; i++) {
                Task currentTask = taskList.get(i);
                if (currentTask.getName().contains(keyword)) {
                    tempTaskList.add(currentTask);
                }
            }

            int tempSize = tempTaskList.size();
            if (tempSize == 0) {
                return "I NEVER FIND ANYTHING! YOU DARE MAKE ME WASTE MY TIME AH?? VERY GOOD!";
            } else {
                String output = "NEED ME HELP YOU FIND AH? VERY GOOD! THIS WEEKEND YOU WATCH OUT!\n";
                output += tempTaskList.printTasks();
                return output;
            }
        }
    }

    /**
     * Handles logic for schedule command.
     * Looks for and displays tasks falling on date.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @return Output of command.
     * @throws DukeException If keyword missing.
     */
    private static String handleSchedule(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT DATE YOU TRYING TO FIND? WAKE UP YOUR BLOODY IDEA!");
        } else {
            DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            String date = inputArgs[1];
            // Converts String to Date.
            LocalDate dateToFind = LocalDate.parse(date, dateInputFormatter);
            // Converts Date back to another String format for comparison.
            String dateToCompare = dateToFind.format(dateOutputFormatter);

            TaskList tempTaskList = new TaskList();
            int size = taskList.size();

            for (int i = 0; i < size; i++) {
                Task currentTask = taskList.get(i);
                if (!currentTask.getType().equals("T") && currentTask.getDate().equals(dateToCompare)) {
                    tempTaskList.add(currentTask);
                }
            }

            int tempSize = tempTaskList.size();
            if (tempSize == 0) {
                return "I NEVER FIND ANYTHING! YOU DARE MAKE ME WASTE MY TIME AH?? VERY GOOD!";
            } else {
                String output = "NEED ME HELP YOU FIND AH? VERY GOOD! THIS WEEKEND YOU WATCH OUT!\n";
                output += tempTaskList.printTasks();
                return output;
            }
        }
    }

    /**
     * Handles logic for deadline command.
     * Creates new Deadline with supplied information.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @return Output of command.
     * @throws DukeException If input is incomplete.
     */
    private static String handleDeadline(TaskList taskList, String[] inputArgs) throws DukeException {
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

            String output = "YOU BETTER FINISH THIS AH:\n"
                    + "  " + newTask
                    + "\nYOU STILL GOT " + taskList.size() + " THINGS TO DO AH BETTER NOT FORGET!";

            return output;
        }
    }

    /**
     * Handles logic for event command.
     * Creates new Event with supplied information.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @return Output of command.
     * @throws DukeException If input is incomplete.
     */
    private static String handleEvent(TaskList taskList, String[] inputArgs) throws DukeException {
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

            String output = "YOU BETTER REMEMBER THIS AH:\n"
                    + "  " + newTask
                    + "\nYOU STILL GOT " + taskList.size() + " THINGS TO DO AH BETTER NOT FORGET!";
            return output;
        }
    }

    /**
     * Handles logic for todo command.
     * Creates new ToDo with supplied information.
     *
     * @param taskList Current list of tasks.
     * @param inputArgs String array made up of individual words from input.
     * @return Output of command.
     * @throws DukeException If input is incomplete.
     */
    private static String handleToDo(TaskList taskList, String[] inputArgs) throws DukeException {
        if (checkIncompleteness(inputArgs)) {
            throw new DukeException("WHAT YOU WANT DO? NOTHING AH HELLOOOOOO?");
        } else {
            Task newTask = new ToDo(inputArgs[1]);
            taskList.add(newTask);
            String output = "YOU WANT TO DO THIS AH:\n"
                    + "  " + newTask
                    + "\nVERY GOOD! YOU STILL GOT " + taskList.size() + " THINGS TO DO AH BETTER NOT FORGET!";
            return output;
        }
    }
}
