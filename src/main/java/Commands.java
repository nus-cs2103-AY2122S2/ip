import java.util.Arrays;
import java.util.Scanner;

public class Commands {
    private String[] fullCommand;
    static Scanner scanner;

    Commands() {
        scanner = new Scanner(System.in);
    }

    private void next() {
        this.fullCommand = scanner.nextLine().split(" ", 2);
    }

    public void response() {
        next();
        String instruction = this.fullCommand[0];
        Task newTask;
        switch (instruction.toLowerCase()) {
            case "":
                response();
                break;
            case "bye":
                Apollo.stop();
                break;
            case "list":
                Apollo.printList();
                response();
                break;
            case "mark":
                Apollo.mark(Integer.parseInt(this.fullCommand[1]), true);
                response();
                break;
            case "unmark":
                Apollo.mark(Integer.parseInt(this.fullCommand[1]), false);
                response();
                break;
            case "todo":
                newTask = new Todo(this.fullCommand[1]);
                Apollo.addTask(newTask);
                response();
                break;
            case "deadline":
                String[] descTime = this.fullCommand[1].split(" */[Bb][Yy] *", 2);
                if (descTime.length != 2) {
                    //todo error
                    return;
                }
                newTask = new Deadline(descTime[0], descTime[1]);
                Apollo.addTask(newTask);
                response();
                break;
            case "event":
                String[] descPeriod = this.fullCommand[1].split(" */[Aa][Tt] *", 2);
                if (descPeriod.length != 2) {
                    //todo error
                    return;
                }
                newTask = new Event(descPeriod[0], descPeriod[1]);
                Apollo.addTask(newTask);
                response();
                break;
            default:
                //todo blah statement
                newTask = new Task(String.join(" ", this.fullCommand));
                Apollo.addTask(newTask);
                response();
        }
    }
}
