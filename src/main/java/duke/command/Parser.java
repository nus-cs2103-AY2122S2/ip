package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.error.DukeException;
import duke.task.*;

/**
 * Handles user input entered into Duke by validating it and making sense out of it. <br>
 */
public class Parser {
    /**
     * Processes and runs the command input by the user.
     * Validation is performed to ensure that the input from the user is of the
     * correct format before it is processed and run.
     *
     * @param ss input entered by the user
     * @param p ui object used to output results
     * @param tl list of task to be updated
     * @return -1: user enters bye, 0 successful run, 1: DukeException is thrown
     */
    public int run(String ss, Ui p, TaskList tl) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String[] args = ss.split("\\s+");
        String action = args[0];
        try {
            validate(ss, action, args, tl.getList());
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return 1;
        }
        switch(action){
            case "bye":
                p.print("Bye. Hope to see you again soon!");
                return -1;
            case "list": 
                if(args.length > 1) {
                    if(args[1].equals("/on")) {
                        ArrayList<Task> result = (ArrayList<Task>) tl.getList()
                                .stream()
                                .filter(t -> t instanceof DeadlineTask)
                                .filter(t -> ((DeadlineTask)t).getDueDate().equals(LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                                .collect(Collectors.toList());
                        result.addAll(tl.getList()
                                .stream()
                                .filter(t -> t instanceof EventTask)
                                .filter(t -> ((EventTask)t).getDate().equals(LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                                .collect(Collectors.toList()));
                        p.print(result);

                    } else if(args[1].equals("/before")) {
                        ArrayList<Task> result = (ArrayList<Task>) tl.getList()
                                .stream()
                                .filter(t -> t instanceof DeadlineTask)
                                .filter(t -> ((DeadlineTask)t).getDueDate().isBefore(LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                                .collect(Collectors.toList());
                        result.addAll(tl.getList()
                                .stream()
                                .filter(t -> t instanceof EventTask)
                                .filter(t -> ((EventTask)t).getDate().isBefore(LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                                .collect(Collectors.toList()));
                        p.print(result);
                    } else if(args[1].equals("/after")) {
                        ArrayList<Task> result = (ArrayList<Task>) tl.getList()
                                .stream()
                                .filter(t -> t instanceof DeadlineTask)
                                .filter(t -> ((DeadlineTask)t).getDueDate().isAfter(LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                                .collect(Collectors.toList());
                        result.addAll(tl.getList()
                                .stream()
                                .filter(t -> t instanceof EventTask)
                                .filter(t -> ((EventTask)t).getDate().isAfter(LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                                .collect(Collectors.toList()));
                        p.print(result);
                    }
                } else {
                    p.print(tl.getList());
                }
                break;
            case "mark":
                tl.getTask(Integer.parseInt(args[1]) - 1).mark();
                p.print("Task marked as done: ", " " + tl.getTask(Integer.parseInt(args[1]) - 1).toString());
                break;
            case "unmark":
                tl.getTask(Integer.parseInt(args[1]) - 1).unmark();
                p.print("Task as not done yet: "," " + tl.getTask(Integer.parseInt(args[1]) - 1).toString());
                break;
            case "todo":
                tl.addTask(new ToDoTask((ss).substring(5, ss.length()).trim()));
                p.print("Added Task: ", " " + tl.getLast().toString(), String.format("There are now %d task(s) in the list.", tl.getSize()));
                break;
            case "deadline":
                tl.addTask(new DeadlineTask(ss.substring(9, ss.length()).split("/by")[0].trim(), LocalDate.parse(ss.split("/by")[1].substring(1, ss.split("/by")[1].length()),formatter)));
                p.print("Added Task: ", " " + tl.getLast().toString(), String.format("There are now %d task(s) in the list.", tl.getSize()));
                break;
            case "event":
                tl.addTask(new EventTask(ss.substring(6, ss.length()).split("/at")[0].trim(), LocalDate.parse(ss.split("/at")[1].substring(1, ss.split("/at")[1].length()),formatter)));
                p.print("Added Task: ", " " + tl.getLast().toString(), String.format("There are now %d task(s) in the list.", tl.getSize()));
                break;
            case "delete":
                Task deletedTask = tl.getTask(Integer.parseInt(args[1]) - 1);
                tl.removeTask(Integer.parseInt(args[1]) - 1);
                p.print("Deleted Task:", " " + deletedTask.toString(), String.format("There are now %d task(s) in the list.", tl.getSize()));
                break;
            default:
                System.out.println("Unknown Command");
        }
        return 0;

    }

    /**
     * Validates the user input to ensure it follows the correct format.
     * Throws DukeException when an incorrect format is detected
     *
     * @param input input entered by the user
     * @param action action performed by the user
     * @param args list of arguments entered by the user
     * @param arr task arrayList to be updated
     */
    public void validate(String input, String action, String[] args, ArrayList<Task> arr) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        switch(action) {
            case "list":
                if(args.length > 1) {
                    if(args[1].equals("/on") || args[1].equals("/before") || args[1].equals("/after")) {
                        try {
                            LocalDate.parse(args[2],formatter);
                        } catch (DateTimeParseException e ) {
                            throw new DukeException("Provide the date in the format dd-mm-yyyy!");
                        } catch (ArrayIndexOutOfBoundsException e ) {
                            throw new DukeException("Provide the date in the format dd-mm-yyyy!");
                        }
                    }
                }
                break;
            case "mark":
            case "unmark":
            case "delete":
                if(args.length == 1)
                    throw new DukeException("Task ID must be provided!");
                try {
                    Integer.parseInt(args[1]);
                } catch(NumberFormatException e) {
                    throw new DukeException("Task ID must be an integer!");
                }
                if(arr.size() == 0)
                    throw new DukeException("Task List is empty!");
                if(Integer.parseInt(args[1]) > arr.size())
                    throw new DukeException("Task ID out of range!");
                break;
            case "todo":
                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");
                break;
            case "deadline":
            case "event":
                String flag = action.equals("deadline") ? "/by" : "/at";
                int actionLength = action.equals("deadline") ? 9 : 6;
                if(args.length == 1)
                    throw new DukeException("Task Name must be provided!");
                if(!Arrays.stream(args).anyMatch(flag::equals))
                    throw new DukeException(String.format("%s flag not detected. Please specify date using %s!", flag, flag));
                if(input.trim().split(flag).length == 1)
                    throw new DukeException(String.format("Please specify deadline date after %s!",flag));
                if (input.substring(actionLength, input.length()).split(flag)[0].trim().equals(""))
                    throw new DukeException("Task Name must be provided!");
                try {
                    LocalDate.parse(input.split(flag)[1].substring(1, input.split(flag)[1].length()),formatter);
                } catch (DateTimeParseException e ) {
                    throw new DukeException("Provide the date in the format dd-mm-yyyy!");
                }
                break;
        }
    }
}
