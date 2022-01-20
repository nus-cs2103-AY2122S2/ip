import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] inputArguments = userInput.split(" ");
            String operation = "";
            try {
                if (inputArguments.length == 0) {
                    throw new DukeEmptyInputException();
                }
                if (inputArguments[0].equals("")) {
                    throw new DukeEmptyInputException();
                }
                operation = inputArguments[0];
            } catch (DukeEmptyInputException e){
                System.out.println(e);
                continue;
            }
            try {
                switch (operation) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case "list":
                        taskManager.list();
                        break;
                    case "mark":
                        if (inputArguments.length < 2) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        try {
                            int markIndex = Integer.parseInt(inputArguments[1]) - 1;
                            taskManager.mark(markIndex);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeTaskNotFoundException();
                        } catch (NumberFormatException e) {
                            throw new DukeInvalidArgumentsException();
                        }
                        break;
                    case "unmark":
                        if (inputArguments.length < 2) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        try {
                            int unmarkIndex = Integer.parseInt(inputArguments[1]) - 1;
                            taskManager.unmark(unmarkIndex);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeTaskNotFoundException();
                        } catch (NumberFormatException e) {
                            throw new DukeInvalidArgumentsException();
                        }
                        break;
                    case "deadline":
                        if (inputArguments.length < 2) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        boolean foundDeadlineCommand = false;
                        for (int i = 0; i < inputArguments.length; i++) {
                            if (inputArguments[i].equals("/by")) {
                                foundDeadlineCommand = true;
                            }
                        }
                        if (!foundDeadlineCommand) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        String[] deadlineDetails = userInput.split("/by", 2);
                        String deadlineTime = deadlineDetails[1].trim();
                        String deadlineName = deadlineDetails[0].substring(operation.length() + 1).trim();
                        if (deadlineTime.isEmpty()) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        if (deadlineName.isEmpty()) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        Deadline deadlineTask = new Deadline(deadlineName, deadlineTime);
                        taskManager.add(deadlineTask);
                        break;
                    case "event":
                        if (inputArguments.length < 2) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        boolean foundEventCommand = false;
                        for (int i = 0; i < inputArguments.length; i++) {
                            if (inputArguments[i].equals("/at")) {
                                foundEventCommand = true;
                            }
                        }
                        if (!foundEventCommand) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        String[] eventDetails = userInput.split("/at", 2);
                        String eventTime = eventDetails[1].trim();
                        String eventName = eventDetails[0].substring(operation.length() + 1).trim();
                        if (eventTime.isEmpty()) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        if (eventName.isEmpty()) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        Event eventTask = new Event(eventName, eventTime);
                        taskManager.add(eventTask);
                        break;
                    case "todo":
                        if (inputArguments.length < 2) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        String toDoName = userInput.substring(operation.length() + 1);
                        ToDo toDoTask = new ToDo(toDoName);
                        taskManager.add(toDoTask);
                        break;
                    case "delete":
                        if (inputArguments.length < 2) {
                            throw new DukeInsufficientArgumentsException();
                        }
                        try {
                            int deleteIndex = Integer.parseInt(inputArguments[1]) - 1;
                            taskManager.delete(deleteIndex);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeTaskNotFoundException();
                        } catch (NumberFormatException e) {
                            throw new DukeInvalidArgumentsException();
                        }
                        break;
                    default:
                        throw new DukeInvalidCommandException();
                }
            } catch (DukeException e){
                System.out.println(e);
            }
        }
    }
}
