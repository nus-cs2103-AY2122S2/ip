import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import myPackage.*;
import exceptions.DukeException;

public class Duke {
    public static void main(String[] args) throws IOException {
        //Task c = new Task("HELLO");
        TaskList.list = new ArrayList<>();
        Save.checkFile();
        Save.load("data/duke.txt");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        int listCount = TaskList.list.size();

        //String[] list = new String[100];
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] userInputSplit = userInput.split(" ");
            String keyword = userInputSplit[0];
            int lenSplit = userInputSplit.length;
            switch (keyword) {
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;
                }
                case "list": {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listCount; i++) {
                        System.out.printf("%d.[%s][%s] %s%n", i + 1, TaskList.list.get(i).getTaskType(),
                                TaskList.list.get(i).getStatusIcon(), TaskList.list.get(i).getDescription());
                    }
                    break;
                }
                case "mark": {
                    if (lenSplit > 1) {
                        TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).markAsDone();
                    }
                    break;
                }
                case "unmark": {
                    if (lenSplit > 1) {
                        TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).unmarkAsDone();
                    }
                    break;
                }
                case "delete": {
                    if (lenSplit > 1) {
                        System.out.println("OK REMOVED\n");
                        TaskList.list.get(Integer.parseInt(userInputSplit[1]) - 1).getFullDescription();
                        TaskList.list.remove(Integer.parseInt(userInputSplit[1]) - 1);
                        listCount--;
                        System.out.printf("\nyou now have %d tasks in the list%n", listCount);
                    }
                    Save.save();
                    break;
                }
                default: {
                    switch (keyword) {
                        case "todo": {
                            try {
                                if (userInputSplit.length < 2) {
                                    throw new DukeException("TODO NEEDS SOMETHING");
                                }
                            } catch (DukeException e) {
                                System.out.println(e);
                                break;
                            }
                            System.out.println("Got it, I've added this task:");
                            TaskList.list.add(new ToDos(userInput));
                            System.out.printf("\nyou now have %d tasks in the list%n", listCount + 1);
                            listCount++;
                            Save.save();
                            break;
                        }
                        case "deadline": {
                            try {
                                if (userInputSplit.length < 2) {
                                    throw new DukeException("Deadline what when how");
                                }
                            } catch (DukeException e) {
                                System.out.println(e);
                                break;
                            }
                            System.out.println("Got it, I've added this task:");
                            TaskList.list.add(new Deadlines(userInput.split("/")[0], userInput.split("/")[1]));
                            System.out.printf("\nyou now have %d tasks in the list%n", listCount + 1);
                            listCount++;
                            Save.save();
                            break;
                        }
                        case "event": {
                            try {
                                if (userInputSplit.length < 2) {
                                    throw new DukeException("event when what how where what");
                                }
                            } catch (DukeException e) {
                                System.out.println(e);
                                break;
                            }
                            System.out.println("Got it, I've added this task:");
                            TaskList.list.add(new Events(userInput.split("/")[0], userInput.split("/")[1]));
                            System.out.printf("\nyou now have %d tasks in the list%n", listCount + 1);
                            listCount++;
                            Save.save();
                            break;
                        }
                        default: {
                            try {
                                throw new DukeException("WDYM by " + userInput);
                            } catch (DukeException e) {
                                System.out.println(e);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
