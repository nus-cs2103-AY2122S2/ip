import main.java.Event;
import main.java.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import main.java.ToDo;
import main.java.Deadline;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public class Duke {

    private static int level = 7;
    private ArrayList<Task> taskArr;
    private int currTask;

    public Duke() {
        this.taskArr = new ArrayList<Task>();
        this.currTask = 0;
    }

    public void load(String filePath) throws FileNotFoundException {
        try {
            File directory = new File("data");
            if (directory.mkdirs()) {
                System.out.println("Created new directory /data");
            }
            File taskTextFile = new File("data/task.txt");
            if (taskTextFile.createNewFile()){
                System.out.println("Created text file as /data/task.txt");
            }

            Scanner s = new Scanner(Paths.get("data/task.txt")); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String words[] = s.nextLine().split(",");
                String done = words[1];
                String description = words[2];
                switch (words[0]) {
                case ("T"):
                    this.taskArr.add(new ToDo(description));
                    break;
                case ("D"):
                    this.taskArr.add(new Deadline(description, words[3]));
                    break;
                case ("E"):
                    this.taskArr.add(new Event(description, words[3]));
                    break;
                } if (done.equals("true")) {
                    taskArr.get(currTask).markDone();
                }
                this.currTask++;
            } if (this.currTask != 0) {
                System.out.println("Previously, you are left with these tasks:");
                list();
                horizontal();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("failed111");
        }
    }

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("data/task.txt");

        for (Task i : taskArr) {
            fw.write(i.toFileFormat());
            fw.write("\n");
        }
        fw.close();
    }

    public void run() {
        welcome();
        try {
            load("data/task.txt");
        } catch (Exception e) {
            System.out.println("failed");
        }
        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")){
                exit();
                end = true;
            } else {
                handle(userCommand);
            }
        }
    }

    public void handle(String userCommand) {
        String words[] = userCommand.split(" ", 2);
        String command = words[0];
        horizontal();
        if (userCommand.equals("list")) {
            list();
        } else if (command.equals("mark")){
            mark(Integer.parseInt(words[1]) - 1);
        } else if (command.equals("unmark")){
            unmark(Integer.parseInt(words[1]) - 1);
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            try {
                addTask(words[1], command);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oh dear. You need to follow the proper format of providing a description for " + command + "!");
                if (command.equals("deadline")) {
                    System.out.println("Follow this format:");
                    System.out.println("deadline YOUR_TASK /by YOUR_DATE");
                    System.out.println("eg: deadline return book /by Sunday");
                } else if (command.equals("event")) {
                    System.out.println("Follow this format:");
                    System.out.println("event YOUR_TASK /at YOUR_DATE");
                    System.out.println("eg: event project meeting /at Mon 2-4pm");
                } else {
                    System.out.println("Follow this format:");
                    System.out.println("todo YOUR_TASK");
                }
            }
        } else if (command.equals("delete")) {
            try {
                delete(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Specify a task to delete!");
            }
        } else {
            System.out.println("Idk what you mean! :-(");
        }
        try {
            writeToFile();
        } catch (Exception IOException) {
            System.out.println("Something went wrong");
        }
        horizontal();
    }

    public void list() {
        if (currTask == 0) {
            System.out.println("No tasks added yet!");
        } else {
            for (int i = 0; i < currTask; i++) {
                System.out.println(i+1 + ". " + taskArr.get(i).toString());
            }
        }
    }

    public void mark(int num) {
        taskArr.get(num).markDone();
        System.out.println("Cool! You seemed to have been productive just like me! I've marked this task as done:");
        System.out.println(taskArr.get(num).toString());
    }

    public void unmark(int num){
        System.out.println("Did you mess up something? Fine... I'll mark it as undone -- but I believe you can do it!:");
        taskArr.get(num).markUndone();
        System.out.println(taskArr.get(num).toString());
    }
    public void exit() {
        horizontal();
        System.out.println("Bye nerd. Glad you'll be interacting with a real life human now! Haha...");
        horizontal();
    }

    public void addTask(String userCommand, String type) {
        if (type.equals("todo")) {
            taskArr.add(new ToDo(userCommand));
        } else if (type.equals("deadline")) {
            String words[] = userCommand.split(" /by ", 2);
            taskArr.add(new Deadline(words[0], words[1]));
        } else {
            String words[] = userCommand.split(" /at ", 2);
            taskArr.add(new Event(words[0], words[1]));
        }

        System.out.println("Roger, I got you. I've added this task:");
        System.out.println(taskArr.get(currTask).toString());
        currTask++;
        System.out.println("Now you have " + currTask + " tasks in the list.");

    }

    public void delete(int taskNum) {
        System.out.println("Noted, I have removed this task:");
        System.out.println(taskArr.get(taskNum).toString());
        taskArr.remove(taskNum);
        currTask--;
        System.out.println("Now you have " + currTask + " tasks in the list.");
    }

    public void horizontal() {
        System.out.println("-------------------------Level " + level + "-------------------------");
    }

    public void welcome(){
        horizontal();
        System.out.println("Greetings, NERD! I'm Duke");
        System.out.println("Fine, I'm programmed to be nice today. What can I do for you? :)");
        horizontal();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
