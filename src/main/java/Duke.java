import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello! I' Duke \nWhat can I do for you?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (sc.hasNextLine()) {
            String command = sc.nextLine().toLowerCase();
            String firstWord;
            int itemIndex = -1;
            int index = command.indexOf(' ');
            if (index > -1) {
                firstWord = command.substring(0, index);
            } else {
                firstWord = command;
            }

            if (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete")) {
                itemIndex = Integer.parseInt(command.substring(index + 1));
            }

            if (command.equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.exit(0);
                sc.close();

            } else if (command.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                taskList.list();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            } else if (firstWord.equals("mark")) {
                if (itemIndex > taskList.length()) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Out of bounds!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    taskList.mark(itemIndex - 1);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(taskList.getTask(itemIndex - 1).toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("unmark")) {
                if (itemIndex > taskList.length()) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Out of bounds!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    taskList.unmark(itemIndex - 1);
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(taskList.getTask(itemIndex - 1).toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }


            } else if (firstWord.equals("delete")) {
                if (itemIndex > taskList.length()) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Out of bounds!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(taskList.getTask(itemIndex - 1).toString());
                    taskList.delete(itemIndex - 1);
                    System.out.println(String.format("Now you have %d task(s) in the list.", taskList.getSize()));
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("todo")) {
                if (index == -1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    String toDoItem = command.substring(index + 1);
                    ToDo toDo = new ToDo(toDoItem);
                    taskList.add(toDo);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Got it, I have added this task:" );
                    System.out.println(toDo.toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("deadline")) {
                if (index == -1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    // add exception checking here
                    if (!command.contains("/by") || command.indexOf("/by") == command.length()) {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Please add a date using /by in dd/mm/yyyy format :)" );
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        String deadlineItem = command.substring(index + 1, command.indexOf("/by") - 1);
                        String time = command.substring(command.indexOf("/by") + 4);
                        Deadline deadline = new Deadline(deadlineItem, time);
                        taskList.add(deadline);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Got it, I have added this task:" );
                        System.out.println(deadline.toString());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                }

            } else if (firstWord.equals("event")) {
                if (index == -1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    if (!command.contains("/at") || command.indexOf("/at") == command.length()) {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Please add a date using /at in dd/mm/yyyy format :)");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        String eventItem = command.substring(index + 1, command.indexOf("/at") - 1);
                        String time = command.substring(command.indexOf("/at") + 4);
                        Event event = new Event(eventItem, time);
                        taskList.add(event);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Got it, I have added this task:");
                        System.out.println(event.toString());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                }

            } else if (command.equals("save")) {
                try {
                    writeToFile("data/tasklist.txt", taskList.saveText());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("File saved!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Please give a proper command!");
                System.out.println("List of commands: ");
                System.out.println("1. todo\n2. deadline\n3. event\n4. list\n5. mark\n6. unmark\n7. delete \n8. bye");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }
}
