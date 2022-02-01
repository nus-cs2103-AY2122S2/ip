package Cleese;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public String handleCommand(String string, TaskList taskList, Ui ui, Storage storage) throws NoDescException, InvalidInputException {
        // Declare DateTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        // split off first word from the rest of the input
        String[] input = string.split(" ", 2);
        String command = input[0];
        String parameters = null;
        if (input.length > 1) {
            parameters = input[1];
        }
        switch (command) {
        case "bye":
            ui.printGoodByeMessage();
            try {
                storage.saveToFile(taskList);
            } catch (Exception e) {
                System.out.println("Error faced when trying to save to file");
            }
            ui.printLine();
            return "bye";
        case "list":
            taskList.printTaskList();
            ui.printLine();
            return "list";
        case "mark":
        case "unmark":
            int selectedTaskNum =  Integer.parseInt(parameters);
            Task selectedTask = taskList.get(selectedTaskNum-1);
            if (command.equals("mark")) {
                selectedTask.markAsDone();
                ui.printMarkedMessage(selectedTask);
            }
            if (command.equals("unmark")) {
                selectedTask.markAsUndone();
                ui.printUnmarkedMessage(selectedTask);
            }
            ui.printLine();
            return "mark/unmark";
        case "delete":
            int deleteSelectedTaskNum = Integer.parseInt(parameters);
            System.out.println(deleteSelectedTaskNum);
            Task deleteSelectedTask = taskList.get(deleteSelectedTaskNum-1);
            taskList.removeFromTaskList(deleteSelectedTask);
            ui.printRemovedAck(deleteSelectedTask, taskList);
            ui.printLine();
            return "delete";
        case "todo":
        case "event":
        case "deadline":
            if (command.equals("todo") && parameters.equals(null)) {
                throw new NoDescException("todo");
            }
            String description = parameters;
            Task newTask = null;
            if (command.equals("todo")) {
                newTask = new Todo(description);
            } else {
                String descriptionAndSetAt = parameters;
                String[] split = descriptionAndSetAt.split("/",2);
                String localDateTimeString = split[1].split(" ", 2)[1];
                LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString, dateTimeFormatter);
                if (command.equals("event")) {
                    newTask = new Event(split[0], localDateTime);
                }
                if (command.equals("deadline")) {
                    newTask = new Deadline(split[0], localDateTime);
                }
            }
            taskList.add(newTask);
            ui.printAddedAck(newTask,taskList);
            ui.printLine();
            return "tasks";
        case "find":
            ui.printFindMessage();
            taskList.find(parameters);
            ui.printLine();
            return "find";
        default:
            throw new InvalidInputException("Invalid Input");
        }
    }
}

