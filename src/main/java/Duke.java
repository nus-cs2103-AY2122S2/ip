import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?\n");

        Tasklist list = new Tasklist();
        Scanner scn = new Scanner(System.in);
        String content = "";
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "desktop", "ip", "data", "duke.txt");
        if (FileCreator.checkDirectory(path)) {
            try {
                content = Files.readString(path);
            } catch (IOException err) {
                System.out.println("Path specified incorrectly.");
            }
        } else {
            FileCreator.createFolder();
        }
        list.setTaskList(content + "\n");
        System.out.println(list.toString());
        while (scn.hasNextLine()) {
            String input = scn.nextLine();
            String[] inputArr = input.split(" ");
            String cmd = inputArr[0];
            try {
                if (cmd.equals(Handlers.Bye.label)) {
                    Handlers.byeHandler(list, String.valueOf(path));
                    break;
                } else if (cmd.equals(Handlers.List.label)) {
                    Handlers.listHandler(list);
                } else if (cmd.equals(Handlers.Deadline.label)) {
                    Handlers.deadlineHandler(list, String.valueOf(path), input, cmd);
                } else if (cmd.equals(Handlers.Event.label)) {
                    Handlers.eventHandler(list, String.valueOf(path), input, cmd );
                } else if (cmd.equals(Handlers.Todo.label)) {
                    Handlers.todoHandler(list, String.valueOf(path), input);
                } else if (cmd.equals(Handlers.Mark.label)) {
                    Handlers.markHandler(list, String.valueOf(path), input);
                } else if (cmd.equals(Handlers.Unmark.label)) {
                    Handlers.unmarkHandler(list, String.valueOf(path), input);
                } else if (cmd.equals(Handlers.Delete.label)) {
                    Handlers.deleteHandler(list, String.valueOf(path), input);
                } else {
                    throw new IllegalArgumentException("Sorry, this is not a recognized command.\n");
                }
            } catch (IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
