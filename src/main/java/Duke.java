import java.util.Scanner;

public class Duke {
    private int repeatListSize = 0;
    private Task[] repeatList = new Task[100];
    private Scanner scanner;

    public Duke(Scanner sc) {
        this.scanner = sc;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke Lumu = new Duke(sc);

        Lumu.botInitialize();
        Lumu.botResponse();
    }

    private String lineBreak() {
        return "\n----------------------------------------\n";
    }

    private void botInitialize() {
        String logo = "LUMU";
//            = " _       _   _   __  __   _   _ \n"
//            + "| |     | | | | |  \\/  | | | | |\n"
//            + "| |     | | | | | |\\/| | | | | |\n"
//            + "| |___  | |_| | | |  | | | |_| |\n"
//            + "|_____|  \\___/  |_|  |_|  \\___/ \n";

        System.out.println(lineBreak() + "Hello I'm\n" + logo);
        System.out.println("What can I do for you?" + lineBreak());
    }

    private void botResponse() {
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine().trim();

            try {
                if (str.compareToIgnoreCase("bye") == 0) {
                    System.out.println("LUMU: Goodbye. Hope to see you again soon");
                    scanner.close();
                    return;
                } else if (str.compareToIgnoreCase("list") == 0) {
                    displayList();
                } else if (str.startsWith("mark ") || str.equals("mark")) {
                    markHandler(str);
                } else if (str.startsWith("unmark ") || str.equals("unmark")) {
                    unmarkHandler(str);
                } else {
                    taskAdder(str);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(lineBreak());
        }
    }

    private void taskAdder(String str) throws DukeException {
        Task task = null;
        Task.TaskType taskType = taskIdentifier(str);
        switch (taskType) {
            case TODO :
                task = new Todo(str.substring(5));
                break;
            case DEADLINE :
                task = new Deadline(str.substring(9, str.lastIndexOf(" /by ")), str.substring(str.lastIndexOf(" /by ") + 5));
                break;
            case EVENT :
                task = new Event(str.substring(6, str.lastIndexOf(" /at ")), str.substring(str.lastIndexOf(" /at ") + 5));
                break;
        }

        if (task != null) {
            repeatList[repeatListSize] = task;
            repeatListSize++;
            System.out.println(String.format("Got it. I've added this task:\n\t%s\nNow you have %d task(s) in the list", task.toString(), repeatListSize));
        }
    }

    private Task.TaskType taskIdentifier(String str) throws DukeException{
        Task.TaskType type = null;
        if (str.matches("(todo|deadline|event).*")) {
            if (str.startsWith("todo")) {
                if (!str.matches("todo\\s\\S+.*")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                type = Task.TaskType.TODO;
            } else if (str.startsWith("deadline")) {
                if (!str.matches("deadline\\s\\S+.*\\s/by\\s\\S+.*")) {
                    throw new DukeException("☹ OOPS!!! The description/date of a deadline cannot be empty.");
                }
                type = Task.TaskType.DEADLINE;
            } else if (str.startsWith("event ")) {
                 if (!str.matches("event\\s\\S+.*\\s/at\\s\\S+.*")) {
                     throw new DukeException("☹ OOPS!!! The description/location of a event cannot be empty.");
                 }
                type = Task.TaskType.EVENT;
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return type;
    }

    private void displayList() {
        if (repeatListSize == 0) {
            System.out.println("LUMU: Your list is empty!");
        } else {
            for (int i = 0; i < repeatList.length && i < repeatListSize; i++) {
                Task currTask = repeatList[i];
                System.out.println(String.valueOf(i + 1) + ". " + currTask.toString());
            }
        }
    }

    private void markHandler(String str) throws DukeException{
        if (str.length() < 6) {
            throw new DukeException("Please choose which task you would like to mark.");
        } else {
            String listNumber = str.substring(5);
            if ((listNumber.chars().allMatch(Character::isDigit)) && (Integer.parseInt(listNumber) <= repeatListSize)) {
                int num = Integer.parseInt(listNumber);
                Task currTask = repeatList[num - 1];
                currTask.setStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currTask.toString());
            } else {
                throw new DukeException("Invalid task chosen to be marked, please try again");            }
        }
    }

    private void unmarkHandler(String str) throws DukeException{
        if (str.length() < 8) {
            throw new DukeException("Please choose which task you would like to unmark.");
        } else {
            String listNumber = str.substring(7);
            if ((listNumber.chars().allMatch(Character::isDigit)) && (Integer.parseInt(listNumber) <= repeatListSize)) {
                int num = Integer.parseInt(listNumber);
                Task currTask = repeatList[num - 1];
                currTask.setStatus(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currTask.toString());
            } else {
                throw new DukeException("Invalid task chosen to be unmarked, please try again");
            }
        }
    }


}
