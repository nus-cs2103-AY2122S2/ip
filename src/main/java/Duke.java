import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {

        ArrayList<DukeBot.Task> arrayList = new ArrayList<>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?\n");

        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));

        PrintWriter pr = new PrintWriter(new BufferedWriter((new OutputStreamWriter((System.out)))));


        while (true) {
            String input = br.readLine();
            String userInput;
            String parts[] = input.split(" ");


            if (parts.length > 1) {
                userInput = parts[0];
            } else {
                userInput = input;
            }

            if (userInput.equals("bye")) {
                pr.print("Bye. Hope to see you again soon!");
                pr.close();
                break;
            } else if (userInput.equals("list")){
                pr.print("Here are the items in your list:" + "\n");
                for (int i = 0; i < arrayList.size(); i++) {
                    DukeBot.Task task = arrayList.get(i);

                    pr.print((i+1) + ".");
                    pr.print(task.toString());
                    pr.print("\n");
                }
                pr.flush();
            } else if (userInput.equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;

                DukeBot.Task task = arrayList.get(index);
                task.toggleCompleted();

                pr.print("Nice! I've marked this task as done:" + "\n");
                pr.print(task.toString() + "\n");
                pr.flush();
            } else if (userInput.equals("unmark")){
                int index = Integer.parseInt(parts[1]) - 1;

                DukeBot.Task task = arrayList.get(index);
                task.toggleUncompleted();

                pr.print("OK, I've marked this task as not done yet:" + "\n");
                pr.print(task.toString() + "\n");
                pr.flush();
            } else if (userInput.equals("todo")) {
                String description = input.substring(5);
                DukeBot.ToDo toDo = new DukeBot.ToDo(description);
                arrayList.add(toDo);

                pr.print("Got it. I've added this task:\n");
                pr.print(toDo.toString() + "\n");
                pr.print("Now you have " + arrayList.size() + " tasks in the list.\n");
                pr.flush();
            } else if (userInput.equals("deadline")) {
                String description = input.substring(9);
                String deadlineParts[] = description.split("/");
                String byWhen = deadlineParts[1].substring(3);

                DukeBot.Deadline deadline = new DukeBot.Deadline(deadlineParts[0], byWhen);
                arrayList.add(deadline);

                pr.print("Got it. I've added this task:\n");
                pr.print(deadline.toString() + "\n");
                pr.print("Now you have " + arrayList.size() + " tasks in the list.\n");
                pr.flush();
            } else if (userInput.equals("event")) {
                String description = input.substring(6);
                String deadlineParts[] = description.split("/");
                String atWhichDay = deadlineParts[1].substring(3);

                DukeBot.Event event = new DukeBot.Event(deadlineParts[0], atWhichDay);
                arrayList.add(event);

                pr.print("Got it. I've added this task:\n");
                pr.print(event.toString() + "\n");
                pr.print("Now you have " + arrayList.size() + " tasks in the list.\n");
                pr.flush();
            } else {
                DukeBot.Task task = new DukeBot.Task(input);
                arrayList.add(task);
                pr.print("added: " + input + "\n");
                pr.flush();
            }
        }
    }
}
