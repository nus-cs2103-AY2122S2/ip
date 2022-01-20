import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Response.LOGO);
        Format.wrapPrint(Response.WELCOME);
        boolean over = false;
        while (!over) {
            String command = myScanner.nextLine();
            String[] words  = command.split(" ");
            String firstWord = words[0];
            if (firstWord.equals("bye")) {
                Format.wrapPrint(Response.GOODBYE);
                over = true;
                continue;
            } else if (firstWord.equals("list")) {
                Format.wrapPrint(taskList.toString());
            } else if (firstWord.equals("mark") && words.length == 2)  {
                try {
                    String change = taskList.markFinished(Integer.parseInt(words[1]));
                    Format.wrapPrint(change);
                } catch (NumberFormatException e) {
                    Format.wrapPrint("mark command must precede with a decimal number!");
                }
            } else if (firstWord.equals("unmark") && words.length == 2)  {
                try {
                    String change = taskList.unmarkFinished(Integer.parseInt(words[1]));
                    Format.wrapPrint(change);
                } catch (NumberFormatException e) {
                    Format.wrapPrint("unmark command must precede with a decimal number!");
                }
            } else if (firstWord.equals("todo"))  {
                try {
                    String content = words[1];
                    for (int i = 2; i < words.length; i++) {
                        content = content + " " + words[i];
                    }
                    Todo newTask = new Todo(content);
                    taskList.addItem(newTask);
                    Format.wrapPrint(Response.ADDED + "\n" + newTask.toString() + "\n"
                                     + Response.taskNo(taskList.size()));
                } catch (IndexOutOfBoundsException e) {
                    Format.wrapPrint("please specify what to do");
                }
            } else if (firstWord.equals("deadline"))  {
                try {
                    String date = command.split("/by ")[1];
                    String content = words[1];
                    for (int i = 2; i < words.length; i++) {
                        if (words[i].equals("/by") )
                            break;
                        content = content + " " + words[i];
                    }
                    Deadline newTask = new Deadline(content, date);
                    taskList.addItem(newTask);
                    Format.wrapPrint(Response.ADDED + "\n" + newTask.toString() + "\n"
                                     + Response.taskNo(taskList.size()));
                } catch (IndexOutOfBoundsException e) {
                    Format.wrapPrint("please specify the date");
                }
            } else if (firstWord.equals("event"))  {
                try {
                    String time = command.split("/at ")[1];
                    String content = words[1];
                    for (int i = 2; i < words.length; i++) {
                        if (words[i].equals("/at"))
                            break;
                        content = content + " " + words[i];
                    }
                    Event  newTask = new Event(content, time);
                    taskList.addItem(newTask);
                    Format.wrapPrint(Response.ADDED + "\n" + newTask.toString() + "\n"
                                     + Response.taskNo(taskList.size()));
                } catch (IndexOutOfBoundsException e) {
                    Format.wrapPrint("please specify the time");
                }
            } else {
                taskList.addItem(new Task(command));
                Format.wrapPrint("added: " + command);
            }
        }
    }
}
