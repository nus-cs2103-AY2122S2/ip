import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static void writeToFile(String filePath, ArrayList<Task> textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath);
            for(Task t: textToAdd) {
                fw.write(t.toString() + System. lineSeparator());
            }
            fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        try {
            File taskFile = new File("taskHistory.txt");
            taskFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        String filePath = "taskHistory.txt";
        ArrayList<Task> taskHistory = new ArrayList<Task>();
        Scanner userInput = new Scanner(System.in);
        String nextInput = userInput.nextLine();

        while (!nextInput.equals("bye")) {
            String[] words = nextInput.split(" ", 2);
            String command = words[0];
            try {
                switch (command) {
                    case "list":
                        int count = 1;
                        System.out.println("Here are the tasks in your list:");
                        for (Task record : taskHistory) {
                            System.out.println(count + ". " + record.toString());
                            count++;
                        }
                        break;
                    case "deadline":
                        if (words.length == 1) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            String[] task = words[1].split("/by ");
                            Deadline d = new Deadline(task[0], task[1]);
                            taskHistory.add(d);
                            try {
                                writeToFile(filePath, taskHistory);
                            } catch (IOException de) {
                                System.out.println("Something went wrong: " + de.getMessage());
                            }
                            System.out.println("Got it. I've added this task:");
                            System.out.println(d.toString());
                            System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
                            break;
                        }
                    case "todo":
                        if (words.length == 1) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } else {
                            String task = words[1];
                            Todo t = new Todo(task);
                            taskHistory.add(t);
                            try {
                                writeToFile(filePath, taskHistory);
                            } catch (IOException te) {
                                System.out.println("Something went wrong: " + te.getMessage());
                            }
                            System.out.println("Got it. I've added this task:");
                            System.out.println(t.toString());
                            System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
                            break;
                        }
                    case "event":
                        if (words.length == 1) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else {
                            String[] task = words[1].split("/at ");
                            Event e = new Event(task[0], task[1]);
                            taskHistory.add(e);
                            try {
                                writeToFile(filePath, taskHistory);
                            } catch (IOException ee) {
                                System.out.println("Something went wrong: " + ee.getMessage());
                            }
                            System.out.println("Got it. I've added this task:");
                            System.out.println(e.toString());
                            System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
                            break;
                        }
                    case "mark":
                        if (words.length == 1) {
                            throw new DukeException("You did not provide a task to mark.");
                        } else {
                            int taskID = Integer.valueOf(words[1]);
                            taskHistory.get(taskID - 1).markDone();
                            try {
                                writeToFile(filePath, taskHistory);
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskHistory.get(taskID - 1).toString());
                            break;
                        }
                    case "unmark":
                        if (words.length == 1) {
                            throw new DukeException("You did not provide a task to unmark.");
                        } else {
                            int taskID = Integer.valueOf(words[1]);
                            taskHistory.get(taskID - 1).markUndone();
                            try {
                                writeToFile(filePath, taskHistory);
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(taskHistory.get(taskID - 1).toString());
                            break;
                        }
                    case "delete":
                        if (words.length == 1) {
                            throw new DukeException("You did not provide a task to delete.");
                        } else {
                            int taskID = Integer.valueOf(words[1]);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(taskHistory.get(taskID - 1).toString());
                            taskHistory.remove(taskID - 1);
                            try {
                                writeToFile(filePath, taskHistory);
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                            System.out.println("Now you have " + taskHistory.size() + " tasks in the list.");
                            break;
                        }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException error){
                System.out.println("OOPS!!! " + error.getMessage());
            }
            nextInput = userInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
