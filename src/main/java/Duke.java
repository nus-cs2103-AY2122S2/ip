import java.util.Scanner;

/**
 * Duke is a simple virtual assistant typically used
 * to teach students about programming.
 */
public class Duke {

    /**
     * Runs Duke.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String nothing = "I can't read your mind, doc! Not yet, anyway.";

        String specific = "You're gonna need to be more specific.\n";
        String tryTodo = "Try 'todo An exciting human task'";
        String tryDeadline = "Try 'deadline A task /by Sunday'";
        String tryEvent = "Try 'event An event /at 7PM'";
        String tryMark = "Try 'mark 2'";
        String tryUnmark = "Try 'unmark 1'";
        String tryDelete = "Try 'delete 3'";

        String unknown = "Sorry, I don't quite understand what you mean by '";

        // Create new variables
        String tempString;
        Echo echo = new Echo();
        Memory memory = new Memory();
        boolean looping = true;

        // Setup disk
        memory.setup();

        // First line
        echo.echoString("Heya! I'm Duke!\n" +
                "What can I do for ya?");

        // While loop
        while(looping) {

            tempString = scanner.nextLine();
            String[] tempStrArray = tempString.split(" ", 2);

            switch (tempStrArray[0]) {
            case "":
                echo.echoString(nothing);
                break;

            case "bye":
                memory.parseUpdateAll();
                looping = false;
                break;

            case "list":
                memory.listAll();
                break;

            case "todo":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + tryTodo);
                } else {
                    //Empty to-do
                    if (tempStrArray[1].equals("")) {
                        echo.echoString(specific + tryTodo);
                    } else {
                            memory.addTask(tempStrArray[1]);
                    }
                }
                break;

            case "deadline":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + tryDeadline);
                } else {
                    String[] tempDeadArray = tempStrArray[1].split(" /by ", 2);

                    if (tempDeadArray.length == 1) {
                        echo.echoString(specific + tryDeadline);
                    } else {
                        if (tempDeadArray[0].equals("")) {
                            echo.echoString(specific + "Don't forget the task's name");
                        } else if (tempDeadArray[1].equals("")) {
                            echo.echoString(specific + "Don't forget the task's deadline");
                        } else {
                            memory.addDeadline(tempDeadArray[0], tempDeadArray[1]);
                        }
                    }
                }
                break;

            case "event":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + tryEvent);
                } else {
                    String[] tempEventArray = tempStrArray[1].split(" /at ", 2);

                    if (tempEventArray.length == 1) {
                        echo.echoString(specific + tryEvent);
                    } else {
                        if (tempEventArray[0].equals("")) {
                            echo.echoString(specific + "Don't forget the event's name");
                        } else if (tempEventArray[1].equals("")) {
                            echo.echoString(specific + "Don't forget the event's time");
                        } else {
                            memory.addEvent(tempEventArray[0], tempEventArray[1]);
                        }
                    }
                }
                break;

            case "mark":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + "Which task do you want me to mark as done?\n" +
                            tryMark);
                } else {
                    String testString = tempStrArray[1];

                    // Test if single number after 'mark'
                    try {
                        int address = Integer.parseInt(testString);
                        memory.setDone(address);
                    }
                    catch (NumberFormatException exception) {
                        echo.echoString("You need to give me a number doc!\n" +
                                tryMark);
                    }
                }
                break;

            case "unmark":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + "Which task do you want me to mark as undone?\n" +
                            tryUnmark);
                } else {
                    String testString = tempStrArray[1];

                    // Test if single number after 'unmark'
                    try {
                        int address = Integer.parseInt(testString);
                        memory.setUndone(address);
                    }
                    catch (NumberFormatException exception) {
                        echo.echoString("You need to give me a number doc!\n" +
                                tryUnmark);
                    }
                }
                break;

            case "delete":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + "Which task do you want me to delete?\n" +
                            tryDelete);
                } else {
                    String testString = tempStrArray[1];

                    // Test if single number after 'delete'
                    try {
                        int address = Integer.parseInt(testString);
                        memory.deleteTask(address);
                    }
                    catch (NumberFormatException exception) {
                        echo.echoString("You need to give me a number doc!\n" +
                                tryDelete);
                    }
                }
                break;

                // If input is unexpected
            default:
                echo.echoString(unknown + tempStrArray[0] + "'");
                break;
            }
        }

        // The last thing Duke says
        echo.echoString("Ok then, see ya!");
    }
}
