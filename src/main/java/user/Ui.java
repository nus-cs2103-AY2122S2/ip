package user;

import exceptions.DukeException;
import tasks.Task;
import tasks.Tasklist;

import java.util.Scanner;

public class Ui {

    public Scanner sc = new Scanner(System.in);
    public Tasklist tasklist;

    public String indent = "    ";
    public String separator = "--------------------------------------------";
    public String[] openingMessage = new String[] {"Hello! I'm Duke!", "What can I do for you?"};
    public String closingMessage = "Bye. Hope to see you again soon!";

    public Parser parser;

    public Ui () throws DukeException {
        tasklist = new Tasklist();
        tasklist.loadTasks();
        parser = new Parser();
    }

    public void printIndent(String s) {
        System.out.println(indent + s);
    }

    public void prettyPrint(String s) {
        printIndent(separator);
        printIndent(s);
        printIndent(separator + "\n");
    }

    public void prettyPrint(String[] messages) {
        printIndent(separator);
        for (String message : messages) printIndent(message);
        printIndent(separator + "\n");
    }

    public void greet() {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        prettyPrint(openingMessage);
    }

    public void sayGoodbye() {
        prettyPrint(closingMessage);
    }

    public void displayTasks() {
        printIndent(separator);
        if (tasklist.getNumTasks() == 0) {
            printIndent("You have no tasks!");
        } else {
            printIndent("Here are the tasks in your list:");
            for (int i = 0; i < tasklist.getNumTasks(); i++) {
                printIndent(String.format("%d. %s", i+1, tasklist.getTask(i)));
            }
        }
        printIndent(separator + "\n");
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void handle(String userInput) {
        try {
             if (userInput.equals("list")) {  // display tasks
                displayTasks();
            } else if (userInput.startsWith("mark ")) {  // mark task as done
                int taskToMark = parser.handleMarkTask(userInput, tasklist.getNumTasks());
                String[] result = tasklist.markTask(taskToMark);
                prettyPrint(result);
            } else if (userInput.startsWith("unmark ")) {  // mark task as undone
                 int taskToUnmark = parser.handleUnmarkTask(userInput, tasklist.getNumTasks());
                 String[] result = tasklist.unmarkTask(taskToUnmark);
                 prettyPrint(result);
            } else if (userInput.startsWith("delete ")) {  // delete task
                 int taskToDelete = parser.handleDeleteTask(userInput, tasklist.getNumTasks());
                 String[] result = tasklist.deleteTask(taskToDelete);
                 prettyPrint(result);
            } else {  // add task
                Task t = parser.addTask(userInput);
                 tasklist.addTask(t);
                 String[] messages = new String[] {
                         "Got it. I've added this task:",
                         t.toString(),
                         "Now you have " + tasklist.getNumTasks() + " tasks in the list."
                 };
                 prettyPrint(messages);
            }
            tasklist.saveTasks();
        } catch (DukeException err) {
            prettyPrint(err.getMessage());
        }
    }
}