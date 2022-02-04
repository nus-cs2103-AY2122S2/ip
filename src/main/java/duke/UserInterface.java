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
    private boolean isLooping = true;

    // Strings
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private String textGreeting = "Heya! I'm Duke! You can see me now!\nWhat can I do for ya?";
    private String guiGreeting = "Heya! I'm Duke! You can see me now!\nWhat can I do for ya?";

    private String nothing = "I can't read your mind, doc! Not yet, anyway.";
    private String saving = "Saving data...";

    private String specific = "You're gonna need to be more specific.\n";
    private String tryFind = "Try 'find keyword'";
    private String tryTodo = "Try 'todo An exciting human task'";
    private String tryDeadline = "Try 'deadline A task /by Sunday'";
    private String tryEvent = "Try 'event An event /at 7PM'";
    private String tryMark = "Try 'mark 2'";
    private String tryUnmark = "Try 'unmark 1'";
    private String tryDelete = "Try 'delete 3'";

    private String dontForgetTaskName = "Don't forget the task's name";
    private String dontForgetTaskDate = "Don't forget the task's deadline";

    private String dontForgetEventName = "Don't forget the Event's name";
    private String dontForgetEventDate = "Don't forget the Event's date";

    private String whichMark = "Which task do you want me to mark as done?\n";
    private String whichUnmark = "Which task do you want me to mark as undone?\n";
    private String whichDelete = "Which task do you want me to delete?\n";
    private String giveNumber = "You need to give me a number doc!\n";

    private String unknown = "Sorry, I don't quite understand what you mean by '";

    private String exitGoodbye = "Ok then, see ya!";

    /**
     * Creates a UserInterface object.
     *
     * @param memory Memory to interface with.
     */
    public UserInterface(Memory memory) {
        this.memory = memory;
    }

    /**
     * Runs the main loop for UserInterface in CLI.
     *
     * Not used, but useful for debugging maybe.
     */
    public void uiStart() {
        scanner = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);

        // First line
        echo.echoString(textGreeting);

        while (isLooping) {

            tempString = scanner.nextLine();
            echo.echoString(uiParse(tempString));

        }
    }

    /**
     * Returns Duke's greeting for GUI.
     * @return Greeting message.
     */
    public String guiStart() {
        return guiGreeting;
    }

    public boolean getIsLooping() {
        return isLooping;
    }

    /**
     * Parses user input and executes actions accordingly.
     * Not to be confused with the Parser class, which parses the save file.
     *
     * @param tempString Raw input string, probably from the user.
     * @return Duke's response upon succeeding or failing to execute the action.
     */
    public String uiParse(String tempString) {
        String[] tempStrArray = tempString.split(" ", 2);
        String retStr;

        switch (tempStrArray[0]) {
        case "":
            return nothing;

        case "bye":
            memory.parseUpdateAll();
            isLooping = false;
            return saving + "\n" + exitGoodbye;

        case "list":
            return memory.listAll();

        case "find":
            if (tempStrArray.length == 1) {
                return specific + tryFind;
            } else {
                //Empty to-do
                if (tempStrArray[1].equals("")) {
                    return specific + tryFind;
                } else {
                    return memory.findAll(tempStrArray[1]);
                }
            }

        case "todo":
            if (tempStrArray.length == 1) {
                return specific + tryTodo;
            } else {
                //Empty to-do
                if (tempStrArray[1].equals("")) {
                    return specific + tryTodo;
                } else {
                    retStr = memory.addTask(tempStrArray[1]);
                    return retStr;
                }
            }

        case "deadline":
            if (tempStrArray.length == 1) {
                return specific + tryDeadline;
            } else {
                String[] tempDeadArray = tempStrArray[1].split(" /by ", 2);

                if (tempDeadArray.length == 1) {
                    return specific + tryDeadline;
                } else {
                    if (tempDeadArray[0].equals("")) {
                        return specific + dontForgetTaskName;
                    } else if (tempDeadArray[1].equals("")) {
                        return specific + dontForgetTaskDate;
                    } else {
                        retStr = memory.addDeadline(tempDeadArray[0], tempDeadArray[1]);
                        return retStr;
                    }
                }
            }

        case "event":
            if (tempStrArray.length == 1) {
                return specific + tryEvent;
            } else {
                String[] tempEventArray = tempStrArray[1].split(" /at ", 2);

                if (tempEventArray.length == 1) {
                    return specific + tryEvent;
                } else {
                    if (tempEventArray[0].equals("")) {
                        return specific + dontForgetEventName;
                    } else if (tempEventArray[1].equals("")) {
                        return specific + dontForgetEventDate;
                    } else {
                        retStr = memory.addEvent(tempEventArray[0], tempEventArray[1]);
                        return retStr;
                    }
                }
            }

        case "mark":
            if (tempStrArray.length == 1) {
                return specific + whichMark + tryMark;
            } else {
                String testString = tempStrArray[1];

                // Test if single number after 'mark'
                try {
                    int address = Integer.parseInt(testString);
                    return memory.setDone(address);
                } catch (NumberFormatException exception) {
                    return giveNumber + tryMark;
                }
            }

        case "unmark":
            if (tempStrArray.length == 1) {
                return specific + whichUnmark + tryUnmark;
            } else {
                String testString = tempStrArray[1];

                // Test if single number after 'unmark'
                try {
                    int address = Integer.parseInt(testString);
                    return memory.setUndone(address);
                } catch (NumberFormatException exception) {
                    return giveNumber + tryUnmark;
                }
            }

        case "delete":
            if (tempStrArray.length == 1) {
                return specific + whichDelete + tryDelete;
            } else {
                String testString = tempStrArray[1];

                // Test if single number after 'delete'
                try {
                    int address = Integer.parseInt(testString);
                    return memory.deleteTask(address);
                } catch (NumberFormatException exception) {
                    return giveNumber + tryDelete;
                }
            }

        // If input is unexpected
        default:
            return unknown + tempStrArray[0] + "'";
        }
    }
}
