import Commands.DeadlineCommand;
import Commands.EventCommand;
import Commands.ToDoCommand;
import Exceptions.DukeException;
import Exceptions.EmptyDescriptionException;
import Exceptions.InvalidMessageException;

import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException, DukeException {

        ArrayList<DukeBot.Task> arrayList = new ArrayList<>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));

        PrintWriter pr = new PrintWriter(new BufferedWriter((new OutputStreamWriter((System.out)))));

        Duke duke = new Duke();

        while (true) {
            try {
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
                } else if (userInput.equals("list")) {
                    pr.print("Here are the items in your list:" + "\n");
                    for (int i = 0; i < arrayList.size(); i++) {
                        DukeBot.Task task = arrayList.get(i);

                        pr.print((i + 1) + ".");
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
                } else if (userInput.equals("unmark")) {
                    int index = Integer.parseInt(parts[1]) - 1;

                    DukeBot.Task task = arrayList.get(index);
                    task.toggleUncompleted();

                    pr.print("OK, I've marked this task as not done yet:" + "\n");
                    pr.print(task.toString() + "\n");
                    pr.flush();
                } else if (userInput.equals("todo")) {
                    DukeBot.ToDo toDo = ToDoCommand.preProcessing(input, parts);
                    arrayList.add(toDo);
                    pr.print(duke.successfulAdd(toDo, arrayList.size()));
                    pr.flush();
                } else if (userInput.equals("deadline")) {
                    DukeBot.Deadline deadline = DeadlineCommand.preProcessing(input, parts);
                    arrayList.add(deadline);
                    pr.print(duke.successfulAdd(deadline, arrayList.size()));
                    pr.flush();
                } else if (userInput.equals("event")) {
                    DukeBot.Event event = EventCommand.preProcessing(input, parts);
                    arrayList.add(event);
                    pr.print(duke.successfulAdd(event, arrayList.size()));
                    pr.flush();
                } else if (userInput.equals("delete")) {
                    int index = Integer.parseInt(parts[1]);

                    DukeBot.Task task = arrayList.remove(index-1);

                    pr.print("Noted. I've removed this task:\n");
                    pr.print(task.toString());
                    pr.print("\nNow you have " + arrayList.size() + " tasks in the list.\n");
                    pr.flush();

                } else {
                    throw new InvalidMessageException();
                }
            } catch (DukeException e) {
                pr.print(e.getMessage() + "\n");
                pr.flush();
            } catch (IndexOutOfBoundsException e) {
                if (arrayList.size() == 0) {
                    pr.print("List is empty! Please add a task before removing/marking it.\n");
                } else {
                    pr.print("Please enter a valid number in the list!\n");
                }
                pr.flush();
            }
        }
    }

    public String successfulAdd(DukeBot.Task task, int listSize) {
        String message = "Got it. I've added this task:\n" + task.toString() + "\n" +
                "Now you have " + listSize + " tasks in the list.\n";
        return message;
    }
}
