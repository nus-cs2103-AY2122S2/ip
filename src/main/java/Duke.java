import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
        List<String> commands = List.of("list", "bye", "mark", "unmark", "delete", "todo", "event", "deadline");

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
                    String response;

                    if(instruction[0].equals("mark")) response = tasks.get(taskNum - 1).markAsDone();
                    else response = tasks.get(taskNum - 1).markAsNotDone();

                    System.out.println(GAP + response);

                } else if (instruction[0].equals("delete")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    System.out.println(String.format(GAP + "Ok, I will remove this task: \n %s", tasks.get(taskNum - 1)));
                    tasks.remove(taskNum - 1);

                } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadline")) {
                    Task newTask;

                    if(instruction[0].equals("todo")) newTask = Todo.of(instruction);
                    else if (instruction[0].equals("event")) newTask = Event.of(instruction);
                    else newTask = Deadline.of(instruction);

                    tasks.add(newTask);
                    System.out.println(GAP + "Got it. I've added this task\n" + GAP +  newTask);
                    System.out.println(String.format(GAP + "You have %d tasks in the list", tasks.size()));

                }
                System.out.println(LINE);
            }
            catch (DukeException e){
                System.out.println(GAP +    e.getMessage());
                System.out.println(LINE);
            }
        }
    }
}
