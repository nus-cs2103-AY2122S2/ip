package duke;

import java.util.Scanner;

/**
 * The UserInterface class connects the user to Memory.
 * This is the equivalent to the 'Parser' class on the example,
 * and is half of the 'Ui' class.
 *
 * @author Rdac0
 */
public class UserInterface {

    private Scanner scanner;
    private Echo echo = new Echo();
    private Memory memory;

    private String tempString;
    boolean isLooping = true;

    /**
     * Creates a UserInterface object.
     *
     * @param memory Memory to interface with.
     */
    public UserInterface(Memory memory) {
        this.memory = memory;
    }

    /**
     * Runs the main loop for UserInterface.
     * Scans user input through CLI and checks for syntax.
     * Runs appropriate methods in Memory.
     */
    public void uiLoop() {
        scanner = new Scanner(System.in);

        // Strings
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String nothing = "I can't read your mind, doc! Not yet, anyway.";
        String saving = "Saving data...";

        String specific = "You're gonna need to be more specific.\n";
        String tryTodo = "Try 'todo An exciting human task'";
        String tryDeadline = "Try 'deadline A task /by Sunday'";
        String tryEvent = "Try 'event An event /at 7PM'";
        String tryMark = "Try 'mark 2'";
        String tryUnmark = "Try 'unmark 1'";
        String tryDelete = "Try 'delete 3'";

        String unknown = "Sorry, I don't quite understand what you mean by '";

        // First line
        echo.echoString("Heya! I'm Duke!\n"
                + "What can I do for ya?");

        while(isLooping) {

            tempString = scanner.nextLine();
            String[] tempStrArray = tempString.split(" ", 2);
            String retStr;

            switch (tempStrArray[0]) {
            case "":
                echo.echoString(nothing);
                break;

            case "bye":
                echo.echoString(saving);
                memory.parseUpdateAll();
                isLooping = false;
                break;

            case "list":
                echo.echoString(memory.listAll());
                break;

            case "todo":
                if (tempStrArray.length == 1) {
                    echo.echoString(specific + tryTodo);
                } else {
                    //Empty to-do
                    if (tempStrArray[1].equals("")) {
                        echo.echoString(specific + tryTodo);
                    } else {
                        retStr = memory.addTask(tempStrArray[1]);
                        echo.echoString(retStr);
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
                            retStr = memory.addDeadline(tempDeadArray[0], tempDeadArray[1]);
                            echo.echoString(retStr);
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
                            retStr = memory.addEvent(tempEventArray[0], tempEventArray[1]);
                            echo.echoString(retStr);
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
                        echo.echoString(memory.setDone(address));
                    } catch (NumberFormatException exception) {
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
                        echo.echoString(memory.setUndone(address));
                    } catch (NumberFormatException exception) {
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
                        echo.echoString(memory.deleteTask(address));
                    } catch (NumberFormatException exception) {
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
