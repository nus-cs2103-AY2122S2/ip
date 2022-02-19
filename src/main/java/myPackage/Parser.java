package myPackage;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static myPackage.TaskList.listCount;

/**
 * Parser class to parse input from user
 */
public class Parser {
    public static String parseCommand(String inputFromUser) {
        Scanner input = new Scanner(System.in);
        String userInput = inputFromUser;
        String[] userInputSplit = userInput.split(" ");
        String keyword = userInputSplit[0];
        int lenSplit = userInputSplit.length;
        String responseFromDuke = "";
        switch (keyword) {
            case "help": {
                responseFromDuke = "Here are the list of commands:\nlist\ntodo\ndeadline\nevent\nmark\nunmark\ndelete\nfind";
                return responseFromDuke;
            }
            case "bye": {
                responseFromDuke = "Bye. Hope to see you again soon!";
                System.exit(0);
                break;
            }
            case "list": {
                responseFromDuke += "Here are the tasks in your list:";
                responseFromDuke += "\n";
                for (int i = 0; i < listCount; i++) {
                    responseFromDuke += String.format("%d.[%s][%s] %s%n\n", i + 1, TaskList.list.get(i).getTaskType(),
                            TaskList.list.get(i).getStatusIcon(), TaskList.list.get(i).getDescription());
                }
                return responseFromDuke;
            }
            case "mark": {
                try {
                    if (lenSplit > 1) {
                        TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).markCheckBoxAs(true);
                        responseFromDuke = TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).markAsDone();
                        return responseFromDuke;
                    }
                    break;
                } catch (Exception e) {
                    return "Invalid input, is your index out of bounds?";
                }

            }
            case "unmark": {
                try {
                    if (lenSplit > 1) {
                        TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).unmarkCheckBox();
                        responseFromDuke = TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).unmarkAsDone();
                        return responseFromDuke;
                    }
                    break;
                } catch (Exception e) {
                    return "Invalid input, is your index out of bounds?";
                }
            }
            case "delete": {
                try {
                    if (lenSplit > 1) {
                        responseFromDuke = "OK REMOVED\n" + TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).getFullDescription()
                                + String.format("\nyou now have %d tasks in the list%n", listCount - 1);
                        TaskList.list.remove(Integer.parseInt(userInputSplit[1]) - 1);
                        listCount--;
                        Storage.save();
                        return responseFromDuke;
                    }
                    break;
                } catch (Exception e) {
                    return "Invalid input, is your index out of bounds?";
                }

            }
            case "find": {
                if (lenSplit > 1) {
                    responseFromDuke += "FINDING...\n";
                    List<Task> l = new ArrayList<>();
                    for (Task task : TaskList.list) {
                        if (task.getDescription().contains(userInputSplit[1])) {
                            l.add(task);
                        }
                    }
                    if (!l.isEmpty()) {
                        responseFromDuke += "THESE ARE YOUR TASKS \n";
                        int printCount = 1;
                        for (Task t : l) {
                            responseFromDuke += String.format(printCount + ". " + t.getFullDescription() + "\n");
                            printCount++;
                        }
                    } else {
                        responseFromDuke += "There doesnt seem to be any tasks found...";
                    }
                    return responseFromDuke;
                } else {
                    responseFromDuke = "No tasks found";
                    return responseFromDuke;
                }
            }

            default: {
                switch (keyword) {
                    case "todo": {
                        try {
                            if (userInputSplit.length < 2) {
                                throw new DukeException("Usage: todo <TaskName>");
                            }

                        } catch (DukeException e) {
                            return e.toString();
                        }
                        responseFromDuke += "Got it, I've added this task:\n";
                        TaskList.list.add(new ToDos(userInput));
                        listCount++;
                        Storage.save();
                        responseFromDuke += TaskList.list.get(listCount - 1).getFullDescription();
                        responseFromDuke += String.format("\nyou now have %d tasks in the list%n", listCount);
                        return responseFromDuke;
                    }
                    case "deadline": {
                        try {
                            if (userInputSplit.length < 2 || userInput.split("/").length < 2) {
                                throw new DukeException("Usage: deadline <Taskname> /by <yyyy-mm-dd>");
                            }
                        } catch (DukeException e) {
                            return e.toString();
                        }
                        responseFromDuke += "Got it, I've added this task:";
                        TaskList.list.add(new Deadlines(userInput.split("/")[0], userInput.split("/")[1]));
                        listCount++;
                        Storage.save();
                        responseFromDuke += TaskList.list.get(listCount - 1).getFullDescription();
                        responseFromDuke += String.format("\nyou now have %d tasks in the list%n", listCount);
                        return responseFromDuke;
                    }
                    case "event": {
                        try {
                            if (userInputSplit.length < 2 || userInput.split("/").length < 2) {
                                throw new DukeException("Usage: event <EventName> /by <yyyy-mm-dd>");
                            }
                        } catch (DukeException e) {
                            return e.toString();
                        }
                        responseFromDuke += "Got it, I've added this task:";
                        TaskList.list.add(new Events(userInput.split("/")[0], userInput.split("/")[1]));
                        listCount++;
                        Storage.save();
                        responseFromDuke += TaskList.list.get(listCount - 1).getFullDescription();
                        responseFromDuke += String.format("\nyou now have %d tasks in the list%n", listCount);
                        return responseFromDuke;
                    }
                    default: {
                        try {
                            throw new DukeException("invalid command: use help for commands");
                        } catch (DukeException e) {
                            return e.toString();
                        }
                    }

                }
            }
        }
        return "error: use help for more info";
    }
}
