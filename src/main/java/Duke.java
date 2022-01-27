import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Duke {
    private static final String LINE = "_____________________________________________________________";
    private static final String GAP = "      ";
    private static final String LOGO =
            "      ████████▄  ███    █▄     ▄█   ▄█▄    ▄████████ \n" +
            "      ███   ▀███ ███    ███   ███ ▄███▀   ███    ███ \n" +
            "      ███    ███ ███    ███   ███▐██▀     ███    █▀  \n" +
            "      ███    ███ ███    ███  ▄█████▀     ▄███▄▄▄     \n" +
            "      ███    ███ ███    ███ ▀▀█████▄    ▀▀███▀▀▀     \n" +
            "      ███    ███ ███    ███   ███▐██▄     ███    █▄  \n" +
            "      ███   ▄███ ███    ███   ███ ▀███▄   ███    ███ \n" +
            "      ████████▀  ████████▀    ███   ▀█▀   ██████████";


    public static void main(String[] args) {

        System.out.println(LINE);
        System.out.println(GAP + "Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println(
                GAP + "I am a chat bot and I'm here to help you be productive :)\n" +
                GAP + "What can I do for you today?"
        );
        System.out.println(LINE);

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        List<String> commands = List.of("list", "bye", "mark", "unmark", "delete", "todo", "event", "deadline", "save");

        try {
            if (Files.exists(Paths.get("data/duke.txt"))) {
                File f = new File("data/duke.txt");
                Scanner fileScanner = new Scanner(f);
                while(fileScanner.hasNextLine()) {
                    List<String> description = Arrays.asList(fileScanner.nextLine().split(" "));
                    Boolean isDone = description.get(0).equals("X");
                    Task task = Task.createTask(description.subList(1, description.size()));
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(GAP + e.getMessage());
            System.out.println(LINE);
        } catch (InvalidArgumentException e) {
            System.out.println(GAP + e.getMessage());
            System.out.println(LINE);
        }

        while (true) {
            try {
                String[] instruction = sc.nextLine().split(" ");
                if (instruction.length == 0 || !commands.contains(instruction[0])) {
                    throw new InvalidCommandException();
                }

                if (instruction[0].equals("bye")) {
                    System.out.println(GAP + "Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    return;

                } else if (instruction[0].equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(GAP + (i + 1) + ". " + tasks.get(i));
                    }

                } else if (instruction[0].equals("mark") || instruction[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(instruction[1]);

                    if (taskNum > tasks.size()) {
                        throw new InvalidIndexException();
                    }

                    String response;

                    if (instruction[0].equals("mark")) {
                        response = tasks.get(taskNum - 1).markAsDone();
                    } else {
                        response = tasks.get(taskNum - 1).markAsNotDone();
                    }

                    System.out.println(GAP + response);

                } else if (instruction[0].equals("delete")) {
                    int taskNum = Integer.parseInt(instruction[1]);

                    if (taskNum > tasks.size()) {
                        throw new InvalidIndexException();
                    }

                    System.out.println(String.format(GAP + "Ok, I will remove this task: \n %s", tasks.get(taskNum - 1)));
                    tasks.remove(taskNum - 1);

                } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadline")) {
                    Task newTask;

                    if(instruction[0].equals("todo")) {
                        newTask = Todo.of(instruction);
                    } else if (instruction[0].equals("event")) {
                        newTask = Event.of(instruction);
                    } else {
                        newTask = Deadline.of(instruction);
                    }

                    tasks.add(newTask);
                    System.out.println(GAP + "Got it. I've added this task\n" + GAP +  newTask);
                    System.out.println(String.format(GAP + "You have %d tasks in the list", tasks.size()));

                } else if (instruction[0].equals("save")) {
                    String output;
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    Path dir = Paths.get("data");
                    Path store = Paths.get("data/duke.txt");
                    if (!Files.exists(dir)) {
                        Files.createDirectory(dir);
                        Files.createFile(store);
                    } else if (!Files.exists(store)) {
                        Files.createFile(store);
                    }

                    FileWriter writer = new FileWriter("data/duke.txt");
                    for (Task task : tasks) {
                        writer.write(task.toStorageString() + "\n");
                    }
                    writer.close();
                }
                System.out.println(LINE);
            }
            catch (DukeException e){
                System.out.println(GAP + e.getMessage());
                System.out.println(LINE);
            } catch (IOException e) {
                System.out.println(GAP + e.getMessage());
                System.out.println(LINE);
            }
        }
    }
}
