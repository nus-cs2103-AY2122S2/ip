package juke;

import java.util.ArrayList;
import java.util.Scanner;

import juke.exception.*;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.Task;
import juke.task.Todo;

public class Juke {
    private static final Juke INSTANCE = new Juke();
    
    private final ArrayList<Task> taskList;
    private final int taskListSize = 100;
    
    public Juke() {
        this.taskList = new ArrayList<>(this.taskListSize);
    }
    
    private void run(Scanner input) {
        this.greet();
        while (true) {
            this.printMarker();
            
            String[] args = input.nextLine().strip().split("\\s+");
            if (args.length > 0) {
                try {
                    switch (args[0]) {
                    case "":
                        break;
                    case "bye":
                        this.bye();
                        break;
                    case "list":
                        this.displayTaskList();
                        break;
                    case "todo":
                        this.addTodoToTaskList(args);
                        break;
                    case "deadline":
                        this.addDeadlineToTaskList(args);
                        break;
                    case "event":
                        this.addEventToTaskList(args);
                        break;
                    case "mark":
                        this.markTaskAsDone(args);
                        break;
                    case "unmark":
                        this.markTaskAsNotDone(args);
                        break;
                    case "delete":
                        this.deleteTaskFromTaskList(args);
                        break;
                    default:
                        this.echo(args);
                    }
                } catch (JukeException e) {
                    this.errorPrint(e);
                }
            }
        }
    }
    
    private void greet() {
        String logo = "                                                             _____   \n"
            + "          _____  ______   _____     ______   _______    _____\\    \\  \n"
            + "         |\\    \\_\\     \\  \\    \\   |\\     \\  \\      \\  /    / |    | \n"
            + "         \\ \\     \\\\    |  |    |    \\\\     \\  |     /|/    /  /___/| \n"
            + "          \\|      ||   |  |    |     \\|     |/     //|    |__ |___|/ \n"
            + "           |      ||    \\_/   /|      |     |_____// |       \\       \n"
            + "   ______  |      ||\\         \\|      |     |\\     \\ |     __/ __    \n"
            + "  /     / /      /|| \\         \\__   /     /|\\|     ||\\    \\  /  \\   \n"
            + " |      |/______/ | \\ \\_____/\\    \\ /_____/ |/_____/|| \\____\\/    |  \n"
            + " |\\_____\\      | /   \\ |    |/___/||     | / |    | || |    |____/|  \n"
            + " | |     |_____|/     \\|____|   | ||_____|/  |____|/  \\|____|   | |  \n"
            + "  \\|_____|                  |___|/                          |___|/   \n";
        System.out.println(logo);
        this.formattedPrint("Greetings Executor!");
    }
    
    private void bye() {
        this.formattedPrint("Until we meet again!");
        System.exit(0);
    }
    
    private void echo(String[] args) {
        this.formattedPrint(String.join(" ", args));
    }
    
    private void addTodoToTaskList(String[] args) throws JukeException {
        if (args.length > 1) {
            if (this.taskList.size() < this.taskListSize) {
                String text = "";
                for (int i = 1; i < args.length; i++) {
                    text += args[i] + " ";
                }
                text = text.strip();
                this.taskList.add(new Todo(text));
                this.formattedPrint("Todo added: " + text);
            } else {
                throw new JukeTaskListFullException("todo");
            }
        } else {
            throw new JukeInvalidArgumentCountException("todo", 2, args.length);
        }
    }
    
    private void addDeadlineToTaskList(String[] args) throws JukeException {
        if (args.length > 3) {
            boolean isValid = false;
            int argIdx = 0;
            for (int j = 2; j < args.length - 1; j++) {
                if (args[j].equals("-by")) {
                    isValid = true;
                    argIdx = j;
                    break;
                }
            }
            if (isValid) {
                if (this.taskList.size() < this.taskListSize) {
                    String text = "";
                    for (int i = 1; i < argIdx; i++) {
                        text += args[i] + " ";
                    }
                    text = text.strip();
                    String time = "";
                    for (int i = argIdx + 1; i < args.length; i++) {
                        time += args[i] + " ";
                    }
                    time = time.strip();
                    this.taskList.add(new Deadline(text, time));
                    this.formattedPrint("Deadline added: " + text);
                } else {
                    throw new JukeTaskListFullException("deadline");
                }
            } else {
                throw new JukeMissingArgumentException("deadline", "-by");
            }
        } else {
            throw new JukeInvalidArgumentCountException("deadline", 4, args.length);
        }
    }
    
    private void addEventToTaskList(String[] args) throws JukeException {
        if (args.length > 3) {
            boolean isValid = false;
            int argIdx = 0;
            for (int j = 2; j < args.length - 1; j++) {
                if (args[j].equals("-at")) {
                    isValid = true;
                    argIdx = j;
                    break;
                }
            }
            if (isValid) {
                if (this.taskList.size() < this.taskListSize) {
                    String text = "";
                    for (int i = 1; i < argIdx; i++) {
                        text += args[i] + " ";
                    }
                    text = text.strip();
                    String time = "";
                    for (int i = argIdx + 1; i < args.length; i++) {
                        time += args[i] + " ";
                    }
                    time = time.strip();
                    this.taskList.add(new Event(text, time));
                    this.formattedPrint("Event added: " + text);
                } else {
                    throw new JukeTaskListFullException("event");
                }
            } else {
                throw new JukeMissingArgumentException("event", "-at");
            }
        } else {
            throw new JukeInvalidArgumentCountException("event", 4, args.length);
        }
    }
    
    private void displayTaskList() throws JukeException {
        if (this.taskList.size() > 0) {
            String str = "";
            for (int i = 0; i < this.taskList.size(); i++) {
                str += (i + 1) + ". " + this.taskList.get(i) + "\n";
            }
            this.formattedPrint(str.stripTrailing());
        } else {
            throw new JukeTaskListEmptyException("list");
        }
    }
    
    private void markTaskAsDone(String[] args) throws JukeException {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.taskList.size()) {
                    throw new JukeInvalidArgumentException("mark", "<juke.task index>", args[1]);
                }
                String des = this.taskList.get(idx - 1).getDescription();
                this.taskList.get(idx - 1).markAsDone();
                this.formattedPrint("Marked juke.task \u00ab" + des + "\u00bb as done.");
            } catch (NumberFormatException e) {
                throw new JukeInvalidArgumentException("mark", "<juke.task index>", args[1]);
            }
        } else {
            throw new JukeInvalidArgumentCountException("mark", 2, args.length);
        }
    }
    
    private void markTaskAsNotDone(String[] args) throws JukeException {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.taskList.size()) {
                    throw new JukeInvalidArgumentException("unmark", "<juke.task index>", args[1]);
                }
                String des = this.taskList.get(idx - 1).getDescription();
                this.taskList.get(idx - 1).markAsNotDone();
                this.formattedPrint("Marked juke.task \u00ab" + des + "\u00bb as not done.");
            } catch (NumberFormatException e) {
                throw new JukeInvalidArgumentException("unmark", "<juke.task index>", args[1]);
            }
        } else {
            throw new JukeInvalidArgumentCountException("unmark", 2, args.length);
        }
    }
    
    private void deleteTaskFromTaskList(String[] args) throws JukeException {
        if (args.length > 1) {
            try {
                int idx = Integer.parseInt(args[1]);
                if (idx <= 0 || idx > this.taskList.size()) {
                    throw new JukeInvalidArgumentException("delete", "<juke.task index>", args[1]);
                }
                String des = this.taskList.get(idx - 1).getDescription();
                this.taskList.remove(idx - 1);
                this.formattedPrint("Removed juke.task \u00ab" + des + "\u00bb from juke.task list.");
            } catch (NumberFormatException e) {
                throw new JukeInvalidArgumentException("delete", "<juke.task index>", args[1]);
            }
        } else {
            throw new JukeInvalidArgumentCountException("delete", 2, args.length);
        }
    }
    
    private void formattedPrint(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }
    
    private void errorPrint(JukeException e) {
        System.out.println("____________________________________________________________");
        System.out.println("\ud83d\ude26 ERROR! " + e.getMessage());
        System.out.println("____________________________________________________________\n");
    }
    
    private void printMarker() {
        System.out.print("\u232c ");
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        INSTANCE.run(input);
        input.close();
    }
}
