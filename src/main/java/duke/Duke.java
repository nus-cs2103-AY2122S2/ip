package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Duke {

    private Storage storage;
    private TaskList list = new TaskList(new ArrayList<>());
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    public void handle(String input) throws DukeException{
        String[] split = input.split(" ");
        String command = split[0];
        if (command.equals("todo")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                Todo t = new Todo(input.substring(5));
                list.add(t);
                System.out.println("Got it. I've added: \n" + t.toString());
                System.out.printf("Sheesh you've now got %d tasks in the list\n", list.size());
            }
        } else if (command.equals("deadline")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                String[] arr = input.substring(9).split("/by ");
                if (arr.length == 1) {
                    throw new DukeException("It seems you haven't included the deadline");
                } else {
                    String[] date = arr[1].split("-");
                    if (date.length != 3) {
                        throw new DukeException("Your deadline should be in the format yyyy-mm-dd");
                    }
                    Deadline d = new Deadline(arr[0],
                            LocalDate.of(Integer.parseInt(date[0]),
                                    Integer.parseInt(date[1]),
                                    Integer.parseInt(date[2])));
                    list.add(d);
                    System.out.println("Got it. I've added: \n" + d.toString());
                    System.out.printf("Sheesh you've now got %d tasks in the list\n", list.size());
                }
            }
        } else if (command.equals("event")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                String[] arr = input.substring(6).split("/at ");
                if (arr.length == 1) {
                    throw new DukeException("It seems you haven't included the event time");
                } else {
                    Event e = new Event(arr[0], arr[1]);
                    list.add(e);
                    System.out.println("Got it. I've added: \n" + e.toString());
                    System.out.printf("Sheesh you've now got %d tasks in the list\n", list.size());
                }
            }
        } else if (command.equals("bye")) {
            try {
                FileWriter fw = new FileWriter("prince.txt");
                for (Integer i = 0; i < list.size(); i++) {
                    fw.write(list.get(i).toString());
                    fw.write(System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("See you later alligator :)");
        } else if (command.equals("list")) {
            if (list.size() == 0) {
                System.out.println("Congrats there's nothing on your list!");
            } else {
                System.out.println("Here's everything on your list rn:");
                for (Integer i = 1; i <= list.size(); i++) {
                    System.out.println(i.toString() + " " + list.get(i - 1));
                }
            }
        } else if (command.equals("mark") || command.equals("unmark")) {
            if (split.length <= 1) {
                throw new DukeException("You need to specify which task :/");
            }
            int idx = Integer.parseInt(split[1]);
            if (idx > list.size()) {
                throw new DukeException("The task number you've given is invalid");
            } else {
                Task t = list.get(idx - 1);
                if (command.equals("unmark")) {
                    t.makeNotDone();
                    System.out.printf("Ok boss I've marked task %s as incomplete\n", split[1]);
                } else {
                    t.makeDone();
                    System.out.printf("Woohoo! I've marked task %s as done\n", split[1]);
                }
                System.out.println(t.toString());
            }
        } else if (command.equals("delete")) {
            if (list.size() == 0) {
                throw new DukeException("There aren't any tasks left to delete");
            } else {
                int idx = Integer.parseInt(split[1]);
                if (idx > list.size()) {
                    throw new DukeException("The task number you've given is invalid");
                } else {
                    Task t = list.get(idx - 1);
                    list.remove(idx - 1);
                    System.out.printf("Alrighty I've removed this task\n");
                    System.out.println(t.toString());
                    System.out.printf("K you've now got %d tasks in the list\n", list.size());
                }
            }
        } else {
            throw new DukeException("I'm not sure what that means");
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String command = sc.nextLine();
                handle(command);
                ui.printDivider();
                if (command.equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
