import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Paths;

public class Duke {
    private enum Dialogue {
        GREETING, FAREWELL, LIST, MARK, UNMARKED, ADDED, GIBBERISH, DELETE, NUMLEFT
    }
    private enum Styling {
        LINE
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Duke.speak(Dialogue.GREETING));
        Duke.runBot();
        System.out.println(Duke.speak(Dialogue.FAREWELL));
    }

    public static void loadFile(String filePath, List<Task> todo) throws IOException {
//        Paths path= Paths.get("saved");
//        boolean directoryFiles = java.nio.file.Files.exists();
        File dir = new File(filePath);
        dir.getParentFile().mkdirs();
        if (dir.createNewFile()) {
            System.out.println("There is no old save file found.\n");
        } else {
            System.out.println("Old save file found on: " + dir.getAbsolutePath()+"\n");
            Scanner sc = new Scanner(dir);
            while (sc.hasNext()) {
                String encoded = sc.nextLine();
                todo.add(Task.deserialize(encoded));
            }
            sc.close();
        }
    }

    public static void appendFile(String filePath, Task todo) throws IOException{
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(todo.serialize());
        fw.close();
    }

    public static void overWriteFile(String filePath, List<Task> todo) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < todo.size(); i++) {
            fw.write(todo.get(i).serialize());
        }
        fw.close();
    }

    public static void runBot() {
        Scanner sc = new Scanner(System.in);
        List<Task> todo = new ArrayList<>();
        String filePath = "/dukeMemory/saved.txt";
        try {
            loadFile(filePath, todo);
        } catch (IOException e){
            System.out.println("Cannot load save file\n");
        }

        while (true) {
            try {
                String[] input = sc.nextLine().split(" ");
                System.out.println(style(Styling.LINE));
                if (input[0].equals("bye")) {
                    break;
                } else {
                    Duke.action(input, todo, filePath);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You don't have that many tasks.\n");
            } catch (DukeException e) {
                System.out.println(e);
            } catch (IOException e ) {
                System.out.println("Problem with save file\n");
            }finally {
                System.out.println(style(Styling.LINE));
            }
        }
        sc.close();
    }

    public static void action(String[] input, List<Task> todo, String filePath) throws DukeException, IOException{
        StringBuilder obj = new StringBuilder("");
        switch (input[0]) {
            case "list":
                System.out.println(Duke.speak(Dialogue.LIST));
                Integer count = 1;
                for (int i = 0; i < todo.size(); i++) {
                    System.out.printf("%d. %s\n", count, todo.get(i));
                    count++;
                }
                break;
            case "mark":
            case "unmark":
                if (input.length != 2) {
                    throw new DukeException("Fill in proper integer for marking/unmarking.\n");
                }
                if (input[0].equals("mark")) {
                    todo.get(Integer.valueOf(input[1]) - 1).mark();
                } else {
                    todo.get(Integer.valueOf(input[1]) - 1).unmarked();
                }
                if (input[0].equals("mark")) {
                    System.out.println(Duke.speak(Dialogue.MARK));
                } else {
                    System.out.println(Duke.speak(Dialogue.UNMARKED));
                }
                System.out.println(todo.get(Integer.valueOf(input[1]) - 1));
                overWriteFile(filePath, todo);
                break;
            case "delete":
                if (input.length != 2) {
                    throw new DukeException("Fill in proper integer for deletion.\n");
                }
                todo.get(Integer.valueOf(input[1]) - 1);
                System.out.println(Duke.speak(Dialogue.DELETE));
                System.out.println(todo.get(Integer.valueOf(input[1]) - 1));
                todo.remove(Integer.valueOf(input[1]) - 1);
                System.out.printf(Duke.speak(Dialogue.NUMLEFT, todo.size()));
                overWriteFile(filePath, todo);
                break;
            case "todo":
                if (input.length == 1) {
                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
                for (int i = 1; i < input.length; i++) {
                    obj.append(input[i]);
                    obj.append(" ");
                }
                System.out.println(Duke.speak(Dialogue.ADDED));
                obj.setLength(obj.length()-1);
                todo.add(new ToDos(obj.toString()));
                System.out.println(todo.get(todo.size()-1));
                System.out.printf(Duke.speak(Dialogue.NUMLEFT, todo.size()));
                appendFile(filePath, todo.get(todo.size()-1));
                break;
            case "deadline":
            case "event":
                if (input.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input[0] + " cannot be empty.\n");
                }
                int i = 1;
                for (; i < input.length; i++) {
                    if (input[i].charAt(0) == '/') break;
                    obj.append(input[i]);
                    obj.append(" ");
                }
                if (i == input.length) {
                    throw new DukeException("☹ OOPS!!! There is no proper date for " + input[0] + ".\n");
                }
                StringBuilder date = new StringBuilder("");
                for (i = i+1; i < input.length; i++) {
                    date.append(input[i]);
                    date.append(" ");
                }
                obj.setLength(obj.length()-1);
                date.setLength(date.length()-1);
                if (input[0].equals("deadline")) {
                    todo.add(new DeadLine(obj.toString(), date.toString()));
                } else {
                    todo.add(new Events(obj.toString(), date.toString()));
                }
                System.out.println(Duke.speak(Dialogue.ADDED));
                System.out.println(todo.get(todo.size()-1));
                System.out.printf(Duke.speak(Dialogue.NUMLEFT, todo.size()));
                appendFile(filePath, todo.get(todo.size()-1));
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    public static String speak(Dialogue option, Integer num) {
        String reply;
        switch (option) {
            case NUMLEFT: reply =  "Now you have " + num.toString() + " task in the list\n";
                break;
            default: reply = "";
        }
        return reply;
    }

    public static String speak(Dialogue option) {
        String reply;
        switch (option) {
            case GREETING: reply =  "Hello! I'm Duke.\nWhat can I do for you?";
                break;
            case MARK: reply = "Nice! I've marked this task as done:\n";
                break;
            case UNMARKED: reply = "OK, I've marked this task as not done yet:\n";
                break;
            case DELETE: reply = "Noted. I've removed this task:\n";
                break;
            case ADDED: reply = "Got it. I've added this task:\n";
                break;
            case LIST: reply = "Here are the tasks in your list:\n";
                break;
            case FAREWELL: reply = "Bye. Hope to see you again soon!\n";
                break;
            default: reply = "Are you finally done?";
                break;
        }
        return reply;
    }

    public static String style(Styling option) {
        String style;
        switch (option) {
            case LINE: style =  "_________________________________________________________";
                break;
            default: style = "";
                break;
        }
        return style;
    }
}
