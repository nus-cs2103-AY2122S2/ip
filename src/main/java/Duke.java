import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    ArrayList<Task> tasks;

    Duke(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    private void mark(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private void addToDo(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n"
                + task + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void addDeadline(Deadline deadline) {
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n"
                + deadline + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void addEvent(Event event) {
        tasks.add(event);
        System.out.println("Got it. I've added this task:\n"
                + event + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void deleteTask(int number) {
        Task taskToDelete = tasks.get(number - 1);
        tasks.remove(number - 1);
        System.out.println("Noted. I've removed this task:\n"
                + taskToDelete + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void saveToFile() throws IOException {
        FileWriter fw = new FileWriter("src/main/data/duke.txt", false);
        for (Task task : tasks) {
            String currTask = String.format("%s ~ %s ~ %s\n",
                    task.getTaskType(),
                    task.getStatusIcon(),
                    task.getDescription());
            fw.write(currTask);
        }
        fw.close();
    }

    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    public void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(intro);

        String cmd = sc.next();
        while (!cmd.equals("bye")) {

            if (cmd.equals(Commands.LIST.command)) {
                listTasks();

            } else if (cmd.equals(Commands.MARK.command)) {
                int taskNumber = 0;
                taskNumber = sc.nextInt();
                mark(taskNumber);

            } else if (cmd.equals(Commands.UNMARK.command)) {
                int taskNumber = 0;
                taskNumber = sc.nextInt();
                unmark(taskNumber);

            } else if (cmd.equals(Commands.TODO.command)){
                String description = sc.nextLine();
                if (description.length() == 0) {
                    throw new DukeException("OOPS!!! The description of " +
                            "a todo cannot be empty.");
                }
                ToDo todo = new ToDo(description);
                addToDo(todo);

            } else if (cmd.equals(Commands.DEADLINE.command)) {
                String info = sc.nextLine();
                String[] split = info.split("/by ");
                String description = split[0];
                String time = split[1];
                Deadline deadline = new Deadline(description, time);
                addDeadline(deadline);

            } else if (cmd.equals(Commands.EVENT.command)) {
                String info = sc.nextLine();
                String[] split = info.split("/at ");
                String description = split[0];
                String time = split[1];
                Event event = new Event(description, time);
                addEvent(event);

            } else if (cmd.equals(Commands.DELETE.command)) {
                int taskNumber = sc.nextInt();
                deleteTask(taskNumber);

            } else {
                throw new DukeException("OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
            }
            saveToFile();
            cmd = sc.next();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static ArrayList<Task> fileToArrayList(String[] currLineContents) {
        ArrayList<Task> listOfSavedTasks = new ArrayList<>();
        String taskType = currLineContents[0];
        boolean isDone = currLineContents[1].equals("X") ? true : false ;
        String description = currLineContents[2];
        if (taskType.equals("T")) {
            listOfSavedTasks.add(new ToDo(description, isDone));
        } else if (taskType.equals("D")) {
            listOfSavedTasks.add(new Deadline(description, isDone, currLineContents[3]));
        } else if (taskType.equals("E")) {
            listOfSavedTasks.add(new Event(description, isDone, currLineContents[3]));
        }
        return listOfSavedTasks;
    }

    public static void main(String[] args) throws DukeException, IOException {
        File f = new File("src/main/data/duke.txt");
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new DukeException("Data file could not be created");
            }
        }
        Scanner sc = new Scanner(f);
        ArrayList<Task> listOfSavedTasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            String[] currLineContents = currLine.split(" ~ ");
            listOfSavedTasks = fileToArrayList(currLineContents);
        }

        Duke duke = new Duke(listOfSavedTasks);
        duke.run();
    }
}
