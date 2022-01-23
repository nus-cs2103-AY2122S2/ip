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
    public int run(String inputString, Ui ui, TaskList taskList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String[] args = inputString.split("\\s+");
        String action = args[0];

        try {
            validate(inputString, action, args, taskList.getList());
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return 1;
        }

        switch(action) {
        case "bye":
            ui.print("Bye. Hope to see you again soon!");
            return -1;
        case "list":
            if(args.length > 1) {
                ArrayList<Task> result;
                switch (args[1]) {
                case "/on":
                    result = (ArrayList<Task>) taskList.getList()
                            .stream()
                            .filter(t -> t instanceof DeadlineTask)
                            .filter(t -> ((DeadlineTask) t).getDueDate()
                                    .equals(LocalDate.parse(args[2],
                                            DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                            .collect(Collectors.toList());
                    result.addAll(taskList.getList()
                            .stream()
                            .filter(t -> t instanceof EventTask)
                            .filter(t -> ((EventTask) t).getDate()
                                    .equals(LocalDate.parse(args[2],
                                            DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                            .collect(Collectors.toList()));
                    ui.print(result);
                    break;
                case "/before":
                    result = (ArrayList<Task>) taskList.getList()
                            .stream()
                            .filter(t -> t instanceof DeadlineTask)
                            .filter(t -> ((DeadlineTask) t).getDueDate()
                                    .isBefore(LocalDate.parse(args[2],
                                            DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                            .collect(Collectors.toList());
                    result.addAll(taskList.getList()
                            .stream()
                            .filter(t -> t instanceof EventTask)
                            .filter(t -> ((EventTask) t).getDate()
                                    .isBefore(LocalDate.parse(args[2],
                                            DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                            .collect(Collectors.toList()));
                    ui.print(result);
                    break;
                case "/after":
                    result = (ArrayList<Task>) taskList.getList()
                            .stream()
                            .filter(t -> t instanceof DeadlineTask)
                            .filter(t -> ((DeadlineTask) t).getDueDate()
                                    .isAfter(LocalDate.parse(args[2],
                                            DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                            .collect(Collectors.toList());
                    result.addAll(taskList.getList()
                            .stream()
                            .filter(t -> t instanceof EventTask)
                            .filter(t -> ((EventTask) t).getDate()
                                    .isAfter(LocalDate.parse(args[2],
                                            DateTimeFormatter.ofPattern("dd/M/yyyy"))))
                            .collect(Collectors.toList()));
                    ui.print(result);
                    break;
                }
            } else {
                ui.print(taskList.getList());
            }
            break;
        case "mark":
            taskList.getTask(Integer.parseInt(args[1]) - 1).mark();
            ui.print("Task marked as done: ", " " + taskList.getTask(Integer.parseInt(args[1]) - 1).toString());
            break;
        case "unmark":
            taskList.getTask(Integer.parseInt(args[1]) - 1).unmark();
            ui.print("Task as not done yet: "," " + taskList.getTask(Integer.parseInt(args[1]) - 1).toString());
            break;
        case "todo":
            taskList.addTask(new ToDoTask((inputString).substring(5).trim()));
            ui.print("Added Task: ", " " + taskList.getLast().toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            break;
        case "deadline":
            taskList.addTask(new DeadlineTask(inputString.substring(9).split("/by")[0].trim(),
                    LocalDate.parse(inputString.split("/by")[1].substring(1),formatter)));
            ui.print("Added Task: ", " " + taskList.getLast().toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            break;
        case "event":
            taskList.addTask(new EventTask(inputString.substring(6).split("/at")[0].trim(),
                    LocalDate.parse(inputString.split("/at")[1].substring(1),formatter)));
            ui.print("Added Task: ", " " + taskList.getLast().toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            break;
        case "delete":
            Task deletedTask = taskList.getTask(Integer.parseInt(args[1]) - 1);
            taskList.removeTask(Integer.parseInt(args[1]) - 1);
            ui.print("Deleted Task:", " " + deletedTask.toString(),
                    String.format("There are now %d task(s) in the list.", taskList.getSize()));
            break;
        default:
            System.out.println("Unknown Command");
        }
        return 0;

    }

    public void validate(String inputString, String action, String[] args, ArrayList<Task> taskArrayList) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        switch(action) {
        case "list":
            if(args.length > 1) {
                if(args[1].equals("/on") || args[1].equals("/before") || args[1].equals("/after")) {
                    try {
                        LocalDate.parse(args[2],formatter);
                    } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e ) {
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
            if(taskArrayList.size() == 0)
                throw new DukeException("Task List is empty!");
            if(Integer.parseInt(args[1]) > taskArrayList.size())
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
            if(Arrays.stream(args).noneMatch(flag::equals))
                throw new DukeException(String
                        .format("%s flag not detected. Please specify date using %s!", flag, flag));
            if(inputString.trim().split(flag).length == 1)
                throw new DukeException(String.format("Please specify deadline date after %s!",flag));
            if (inputString.substring(actionLength).split(flag)[0].trim().equals(""))
                throw new DukeException("Task Name must be provided!");
            try {
                LocalDate.parse(inputString.split(flag)[1].substring(1),formatter);
            } catch (DateTimeParseException e ) {
                throw new DukeException("Provide the date in the format dd-mm-yyyy!");
            }
            break;
        }
    }
}
