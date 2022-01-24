import java.util.Scanner;
import java.util.StringTokenizer;

public class Ui {
    Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    void printBootUp() {
        System.out.println("Hello! I am Duck 🐥\nWhat can I do for you?");
    }

    void printLoadingError() {
        System.out.println("Since loading the save file has failed, your task list is currently empty");
    }

    void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    void printException(DukeException e) {
        System.out.println(String.format("%s *quack*", e.getMessage()));
    }

    void printHelp() {
        System.out.println("These are the commands you can use *quack*:");
        System.out.println("  'list' to list out all your tasks");
        System.out.println("  'todo <description>' to add a todo task");
        System.out.println("  'deadline /<preposition> <description>' to add a task with a deadline");
        System.out.println("  'event /<preposition> <description>' to add an event with a date");
        System.out.println("  'mark <task number>' to mark a task as done");
        System.out.println("  'unmark <task number>' to unmark a task as done");
        System.out.println("  'delete <task number>' to delete a task");
        System.out.println("  'bye' to close your Duck app");
        System.out.println("*quack*");
    }

    void print(String message) {
        System.out.println(message);
    }

    String readCommand() {
        return this.sc.nextLine();
    }
}
