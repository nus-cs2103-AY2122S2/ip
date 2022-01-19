import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskArr = new ArrayList<>();
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

        do {
            input = Console.read();
            String[] inputArgs = input.split(" ", 2);

            try {
                switch (inputArgs[0].toLowerCase()) {
                    case "bye": {
                        Command.checkSingle(inputArgs);
                        switch (dot) {
                            case JJBA: {
                                Console.println("Goodbye! Good luck!");
                                break;
                            }
                            case DIO: {
                                Console.println("What?! I-Impossible... I-I am Dio... I am the mighty Dio!");
                            }
                        }
                        exitProg = true;
                        break;
                    }
                    case "dio": {
                        Command.checkSingle(inputArgs);

                        dot = BOT.DIO;
                        Console.println("ZA WARUDO!");
                        break;
                    }
                    case "jjba": {
                        Command.checkSingle(inputArgs);

                        dot = BOT.JJBA;
                        Console.println("What can I do for you?");
                        break;
                    }
                    case "list": {
                        Command.checkSingle(inputArgs);
                        switch (dot) {
                            case JJBA: {
                                Console.printList("Here are the things you will need to do:",
                                        "There are no task available.",taskArr);
                                break;
                            }
                            case DIO: {
                                Console.printList("Oh? You're Approaching Me?",
                                        "I reject my humanity, Jojo!", taskArr);
                            }
                        }
                        break;
                    }
                    case "delete" : {
                        Task curTask = taskArr.remove(Integer.parseInt(inputArgs[1]) - 1);

                        switch (dot) {
                            case JJBA: {
                                Console.print("Task removed!\n   " + curTask.toString());
                                break;
                            }
                            case DIO: {
                                Console.print("*throws knifes\n   " + curTask.toString());
                            }
                        }
                        printTaskLeft();
                        break;
                    }
                    case "mark": {
                        Task curTask = taskArr.get(Integer.parseInt(inputArgs[1]) - 1);
                        curTask.markTask();

                        switch (dot) {
                            case JJBA: {
                                Console.println("Good job on completing your task!\n   " + curTask.toString());
                                break;
                            }
                            case DIO: {
                                Console.println("KONO DIO DA!\n   " + curTask.toString());
                            }
                        }
                        break;
                    }
                    case "unmark": {
                        Task curTask = taskArr.get(Integer.parseInt(inputArgs[1]) - 1);
                        curTask.unmarkTask();

                        switch (dot) {
                            case JJBA: {
                                Console.println("Done, remember to do your task.\n   " + curTask.toString());
                                break;
                            }
                            case DIO: {
                                Console.println("Hinjaku! Hinjaku!\n   " + curTask.toString());
                            }
                        }
                        break;
                    }
                    case "todo": {
                        Task newTask = Command.createTodo(inputArgs);

                        taskArr.add(newTask);
                        printTaskAdd(newTask);
                        break;
                    }
                    case "deadline": {
                        Task newTask = Command.createDeadline(inputArgs);

                        taskArr.add(newTask);
                        printTaskAdd(newTask);
                        break;
                    }
                    case "event": {
                        Task newTask = Command.createEvent(inputArgs);

                        taskArr.add(newTask);
                        printTaskAdd(newTask);
                        break;
                    }
                    default: {
                        throw new DukeException("Invalid Command.");
                    }
                }
            } catch (DukeException e) {
                switch (dot) {
                    case JJBA: {
                        Console.println("⚠ " + e.getMessage());
                        break;
                    }
                    case DIO: {
                        Console.println("⚠ RODA ROLLA DA!");
                    }
                }

            } catch (NumberFormatException e) {
                switch (dot) {
                    case JJBA: {
                        Console.println("⚠ Invalid input, please enter a task number.");
                        break;
                    }
                    case DIO: {
                        Console.println("⚠ RODA ROLLA DA!");
                    }
                }

            } catch (IndexOutOfBoundsException e) {
                switch (dot) {
                    case JJBA: {
                        Console.println("⚠ Invalid task number, pleased enter a valid task number.");
                        break;
                    }
                    case DIO: {
                        Console.println("⚠ RODA ROLLA DA!");
                    }
                }
            }

        } while (!exitProg);

    }

    public static void printTaskAdd(Task newTask) {
        switch(dot) {
            case JJBA: {
                Console.print("Task added: \n" + newTask);
                break;
            }
            case DIO: {
                Console.print("WRYYYYYYYYYYYY! \n" + newTask);
                break;
            }
        }
        printTaskLeft();
    }

    public static void printTaskLeft() {
        switch(dot) {
            case JJBA: {
                Console.println(String.format("You have %s task%s in your list.",
                        (taskArr.size() > 0) ? taskArr.size() : "no", (taskArr.size() <= 1) ? "" : "s"));
                break;
            }
            case DIO: {
                String muda = "";
                for (int i = 0; i < taskArr.size(); i++) {
                    muda += (i == taskArr.size() - 1) ? "MUDA!" : "MUDA ";
                }

                if (muda.isEmpty()) {
                    Console.println("NANI!");
                } else {
                    Console.println(muda);
                }

            }
        }
    }

    public enum BOT {
        JJBA,
        DIO
    }
}
