import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static TaskList taskList = new TaskList();
    public static BOT dot = BOT.JJBA;


    public static void main(String[] args) {
        String logo = "   ___   _________  ___  \n" +
                      "  |_  | |_  | ___ \\/ _ \\ \n" +
                      "    | |   | | |_/ / /_\\ \\\n" +
                      "    | |   | | ___ \\  _  |\n" +
                      "/\\__/ /\\__/ / |_/ / | | |\n" +
                      "\\____/\\____/\\____/\\_| |_/";
        Console.print(logo);
        Console.println("Hello, I'm JJBA\nWhat can I do for you?");

        String input = "";
        boolean exitProg = false;

        Storage.initDataInfo();
        taskList = Storage.loadTaskList();

        do {
            input = Console.read();
            String[] inputArgs = input.split(" ", 2);

            try {
                switch (inputArgs[0].toLowerCase()) {
                case "bye":
                    Command.checkSingle(inputArgs);
                    switch (dot) {
                    case JJBA:
                        Console.println("Goodbye! Good luck!");
                        break;
                    case DIO:
                        Console.println("What?! I-Impossible... I-I am Dio... I am the mighty Dio!");
                        break;
                    }
                    exitProg = true;
                    break;

                case "dio":
                    Command.checkSingle(inputArgs);

                    dot = BOT.DIO;
                    Console.println("ZA WARUDO!");
                    break;

                case "jjba":
                    Command.checkSingle(inputArgs);

                    dot = BOT.JJBA;
                    Console.println("What can I do for you?");
                    break;

                case "list":
                    Command.checkSingle(inputArgs);
                    switch (dot) {
                    case JJBA:
                        Console.printList("Here are the things you will need to do:",
                                    "There are no task available.", taskList);
                        break;
                    case DIO:
                        Console.printList("Oh? You're Approaching Me?",
                                "I reject my humanity, Jojo!", taskList);
                        break;
                    }
                    break;

                case "delete" :
                    Task curTask = taskList.get(Integer.parseInt(inputArgs[1]) - 1);

                    taskList.remove(curTask);

                    switch (dot) {
                        case JJBA:
                            Console.print("Task removed!\n   " + curTask.toString());
                            break;
                        case DIO:
                            Console.print("*throws knifes\n   " + curTask.toString());
                            break;
                    }
                    printTaskLeft();
                    break;

                case "mark":
                    curTask = taskList.get(Integer.parseInt(inputArgs[1]) - 1);
                    curTask.markTask();

                    switch (dot) {
                    case JJBA:
                        Console.println("Good job on completing your task!\n   " + curTask.toString());
                        break;
                    case DIO:
                        Console.println("KONO DIO DA!\n   " + curTask.toString());
                        break;
                    }
                    break;

                case "unmark":
                    curTask = taskList.get(Integer.parseInt(inputArgs[1]) - 1);
                    curTask.unmarkTask();

                    switch (dot) {
                    case JJBA:
                        Console.println("Done, remember to do your task.\n   " + curTask.toString());
                        break;
                    case DIO:
                        Console.println("Hinjaku! Hinjaku!\n   " + curTask.toString());
                        break;
                    }
                    break;
                case "todo":
                    Task newTask = Command.createTodo(inputArgs);

                    taskList.add(newTask);
                    printTaskAdd(newTask);
                    break;
                case "deadline":
                    newTask = Command.createDeadline(inputArgs);

                    taskList.add(newTask);
                    printTaskAdd(newTask);
                    break;
                case "event":
                    newTask = Command.createEvent(inputArgs);

                    taskList.add(newTask);
                    printTaskAdd(newTask);
                    break;
                default:
                    throw new DukeException("Invalid Command.");
                }

                Storage.saveTaskList(taskList);

            } catch (DukeException e) {
                switch (dot) {
                case JJBA:
                    Console.println("⚠ " + e.getMessage());
                    break;
                case DIO:
                    Console.println("⚠ RODA ROLLA DA!");
                    break;
                }

            } catch (NumberFormatException e) {
                switch (dot) {
                    case JJBA:
                        Console.println("⚠ Invalid input, please enter a task number.");
                        break;
                    case DIO:
                        Console.println("⚠ RODA ROLLA DA!");
                        break;
                }

            } catch (IndexOutOfBoundsException e) {
                switch (dot) {
                    case JJBA:
                        Console.println("⚠ Invalid task number, pleased enter a valid task number.");
                        break;
                    case DIO:
                        Console.println("⚠ RODA ROLLA DA!");
                        break;
                }
            } catch (IOException e) {
                Console.println(e.getMessage());
            }


        } while (!exitProg);

    }

    public static void printTaskAdd(Task newTask) {
        switch(dot) {
        case JJBA:
            Console.print("Task added: \n" + newTask);
            break;
        case DIO:
            Console.print("WRYYYYYYYYYYYY! \n" + newTask);
            break;
        }
        printTaskLeft();
    }

    public static void printTaskLeft() {
        switch(dot) {
        case JJBA:
            Console.println(String.format("You have %s task%s in your list.",
                    (taskList.size() > 0) ? taskList.size() : "no", (taskList.size() <= 1) ? "" : "s"));
            break;
        case DIO:
            String muda = "";
            for (int i = 0; i < taskList.size(); i++) {
                muda += (i == taskList.size() - 1) ? "MUDA!" : "MUDA ";
            }

            if (muda.isEmpty()) {
                Console.println("NANI!");
            } else {
                Console.println(muda);
            }
            break;
        }
    }

    public enum BOT {
        JJBA,
        DIO
    }
}
