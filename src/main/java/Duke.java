import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        String welcome = "Hello! I'm ChatCat\n" + "What can I do for you?\n";
        System.out.println(welcome);
        TaskList taskList = new TaskList();

        String input = " ";

        try {
            while (!input.equals("bye")) {
                input = br.readLine();
                String[] splitInput = input.split(" ");

                switch(splitInput[0]) {
                    case "list":
                        taskList.listTasks();
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "mark":
                        taskList.mark(input);
                        break;
                    case "unmark":
                        taskList.unmark(input);
                        break;
                    case "todo":
                        taskList.setTodo(input);
                        break;
                    case "deadline":
                        taskList.setDeadline(input);
                        break;
                    case "event":
                        taskList.setEvent(input);
                        break;
                    case "delete":
                        taskList.delete(input);
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
                }
            }
        } catch (DukeException wrf) {
            wrf.printStackTrace();
        }
    }
}
