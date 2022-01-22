import java.util.Scanner;

public class Duke {
    //In future iterations: Make Identity, TaskList as a initialized class.
    private final static Identity thisIdentity = new Identity();
    private final static TaskList thisList = new TaskList();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String nextKey = scanner.nextLine();

            //use Enum for command keys?

            if (Identity.exitMatch(nextKey)) {
                break;
            }

            if (Identity.listMatch(nextKey)) {
                System.out.println(
                        "----------------------------" +
                                "----------------------------\n"
                        + Duke.thisList.printList()
                        + "--------------------------------------------------------"
                );
                continue;
            }

            if (Identity.markMatch(nextKey)) {
                //mark
                Duke.thisList.markTask(nextKey);
                continue;
            }

            if (Identity.unmarkMatch(nextKey)) {
                //unmark
                Duke.thisList.unmarkTask(nextKey);
                continue;
            }

            if (Identity.todoMatch(nextKey)) {
                //unmark
                Duke.thisList.addTodo(nextKey);
                continue;
            }

            if (Identity.eventMatch(nextKey)) {
                //unmark
                Duke.thisList.addEvent(nextKey);
                continue;
            }

            if (Identity.deadlineMatch(nextKey)) {
                //unmark
                Duke.thisList.addDeadline(nextKey);
                continue;
            }

        }

        Identity.exitProg();
    }
}
