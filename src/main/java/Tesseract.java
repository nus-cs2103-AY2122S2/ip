import java.util.*;

public class Tesseract {
    public static void main(String[] args) {
        String INDENT1 = "    ";
        String INDENT2 = "        ";
        String BREAKER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        List<Task> taskList = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);

        String greetingMsg = BREAKER + "\n" + "Hi fellow! I am Tesseract\n" +
                "I can bring you to wherever you want in the universe\n" +
                "How can I help you?\n"
                + BREAKER;

        String farewellMsg = BREAKER + "\n" +
                "Ok I'm gonna travel to another planet now\n" +
                "Hope to see you again when I'm back :P\n"
                + BREAKER;

        // greet the user
        System.out.println(greetingMsg);

        String cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            String msg = "";
            if (cmd.equals("list")) {
                msg = "Look what I have noted down for you~ \n";
                for (int i = 0; i < taskList.size(); i++) {
                    msg += INDENT2 + (i+1) + "." + taskList.get(i).toString() + "\n";
                }
            } else if (cmd.length() > 5 && cmd.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(cmd.substring(5)) - 1;
                taskList.get(index).markAsDone();
                msg = "Wow you have finished a task! Excellent! \n" + INDENT2 + taskList.get(index).toString() + "\n";
            } else if (cmd.length() > 7 && cmd.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(cmd.substring(7)) - 1;
                taskList.get(index).markAsUndone();
                msg = "Seems like you have successfully undone your done task \n" + INDENT2 + taskList.get(index).toString() + "\n";
            } else {
                int indexAt = cmd.indexOf("/at");
                int indexBy = cmd.indexOf("/by");
                Task newTask;
                if (indexAt != -1) { // Event
                    newTask = new Event(cmd.substring(0, indexAt - 1), cmd.substring(indexAt + 4));
                } else if (indexBy != -1) { // Deadline
                    newTask = new Deadline(cmd.substring(0, indexBy - 1), cmd.substring(indexBy + 4));
                } else { // _Todo
                    newTask = new Todo(cmd);
                }
                taskList.add(newTask);
                msg = "This has been added to your schedule. Wish you can finish it on time\n"
                        + INDENT2 + newTask.toString() + "\n" + INDENT1
                        + "Now you have " + taskList.size() + " tasks waiting to be finished.\n";
            }

            String out = INDENT1 + BREAKER + "\n" + INDENT1 + msg + INDENT1 + BREAKER + "\n";
            System.out.println(out);
            cmd = sc.nextLine();
        }
        System.out.println(farewellMsg);
    }
}
